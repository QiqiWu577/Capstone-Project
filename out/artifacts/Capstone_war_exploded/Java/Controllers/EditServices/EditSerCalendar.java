package Controllers.EditServices;

import Persistance.FullcalendarDBOps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import Model.CalendarDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet(name = "EditSerCalendar",urlPatterns = "/EditSerCalendar")
public class EditSerCalendar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        FullcalendarDBOps DBOps = new FullcalendarDBOps();
        ArrayList<CalendarDAO> list;

        //get the calendar event from the ajax function of the js
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String id= data.get("id").getAsString();
        String employee = data.get("title").getAsString();
        String currentStart = data.get("start").getAsString();
        String currentEend = data.get("end").getAsString();
        String type = data.get("type").getAsString();

        //get the shiftList from the session based on the type
        if(type.equals("S")){
            list = (ArrayList<CalendarDAO>)session.getAttribute("serverShifts");
        }else if(type.equals("B")){
            list = (ArrayList<CalendarDAO>) session.getAttribute("bartenderShifts");
        }else{
            list = (ArrayList<CalendarDAO>) session.getAttribute("kichenShifts");
        }

        LocalDateTime cs = LocalDateTime.parse(currentStart);
        LocalDateTime ce = LocalDateTime.parse(currentEend);
        int newDayId = 0;
        int newShiftId = 0;
        int newEmpId = 0;
        String compareCheck;

        if(id.equals("add")){

            //check if the employee exists
            newEmpId = DBOps.empExist(employee);
            if(newEmpId == 0){
                response.getWriter().write("noEmp");
            }else{

                //check if the day exists
                newDayId = DBOps.dayExist(cs);
                if(newDayId == 0){
                    //add a new day in days table
                    newDayId = DBOps.addDay(cs,ce);

                    //add a new shift in the shift table
                    newShiftId = DBOps.addShift(newEmpId,newDayId,cs,ce);
                }else{

                    //get the ops hours of the newDayId
                    String opsHours =  DBOps.getOpsHours(newDayId);
                    String [] hours = opsHours.split(",");
                    LocalDateTime sO = LocalDateTime.parse(hours[0].replace(" ","T"));
                    LocalDateTime eO = LocalDateTime.parse(hours[1].replace(" ","T"));

                    //compare the new shift and the new day in the days table
                    //return the condition that which data needed to be updated
                    compareCheck = compareDayOps(cs,ce,sO,eO);
                    if(compareCheck.equals("s")){

                        boolean test = DBOps.updateDayOps(newDayId,cs,ce,1);
                    }else if(compareCheck.equals("e")){

                        boolean test = DBOps.updateDayOps(newDayId,cs,ce,2);
                    }else if(compareCheck.equals("both")){

                        boolean test = DBOps.updateDayOps(newDayId,cs,ce,3);
                    }

                    //check if the newShift exists in the shift table
                    //if it doesn't, add a new shift.update the join table
                    newShiftId = DBOps.shiftExist(cs,ce);

                    if(newShiftId == 0){

                        newShiftId = DBOps.addShift(newEmpId,newDayId,cs,ce);

                    }else{

                        boolean flag = DBOps.checkEmployeeShift(newEmpId,cs);
                        if(flag){

                            //check the same employee situations
                            //they cannot be the same time range or cross over
                            //if it is, display the error and do nothing. or else continue the operation on shift table
                            String result = DBOps.checkSameShift(newEmpId,newShiftId,cs,ce);
                            if(result.equals("sameShiftRange")){
                                response.getWriter().write("sameShiftRange");
                            }else if(result.equals("crossover")){
                                response.getWriter().write("crossover");
                            }
                        }
                    }
                }
                //delete the old one and add the new one to the join table for the new shift
                //boolean test = DBOps.updateEmpShift(-1,newShiftId,newEmpId);
            }

        }else if(employee.equals("delete")){

            CalendarDAO change = list.get(Integer.parseInt(id));
            int oldShiftId = change.getShiftId();
            int empId = change.getEmpId();         //used to update the schedule_employee table for the shift

            boolean test = DBOps.deleteShift(empId,oldShiftId);
            if(test){
                response.getWriter().write("Delete successful!");
            }else{
                response.getWriter().write("Fail to delete the shift.");
            }

        }else{

            CalendarDAO change = list.get(Integer.parseInt(id));
            int oldShiftId = change.getShiftId();
            int oldDayId = change.getDayId();      //used to get the operation hours and save change into day table
            int empId = change.getEmpId();         //used to update the schedule_employee table for the shift

            //check if they are the same shift
            //if they are, do nothing. or else continue to check
            if(DBOps.checkSameShift(oldShiftId, cs, ce)){

            }else{

                //check if the day exists
                //if it doesn't, add a new day. or else compare the shift and the day to
                //see whether or not to chang the date in the day table
                newDayId = DBOps.dayExist(cs);
                if(newDayId == 0){

                    //add a new day in days table
                    newDayId = DBOps.addDay(cs,ce);

                    //add a new shift in the shift table
                    newShiftId = DBOps.addShift(empId,newDayId,cs,ce);

                }else{

                    //get the ops hours of the newDayId
                    String opsHours =  DBOps.getOpsHours(newDayId);
                    String [] hours = opsHours.split(",");
                    LocalDateTime sO = LocalDateTime.parse(hours[0].replace(" ","T"));
                    LocalDateTime eO = LocalDateTime.parse(hours[1].replace(" ","T"));

                    //compare the new shift and the new day in the days table
                    //return the condition that which data needed to be updated
                    compareCheck = compareDayOps(cs,ce,sO,eO);
                    if(compareCheck.equals("s")){

                        boolean test = DBOps.updateDayOps(newDayId,cs,ce,1);
                    }else if(compareCheck.equals("e")){

                        boolean test = DBOps.updateDayOps(newDayId,cs,ce,2);
                    }else if(compareCheck.equals("both")){

                        boolean test = DBOps.updateDayOps(newDayId,cs,ce,3);
                    }

                    //check if the newShift exists in the shift table
                    //if it doesn't, add a new shift.update the join table
                    newShiftId = DBOps.shiftExist(cs,ce);

                    if(newShiftId == 0){

                       newShiftId = DBOps.addShift(empId,newDayId,cs,ce);

                    }else{

                        boolean flag = DBOps.checkEmployeeShift(empId,cs);
                        if(flag){

                            //check the same employee situations
                            //they cannot be the same time range or cross over
                            //if it is, display the error and do nothing. or else continue the operation on shift table
                            String result = DBOps.checkSameShift(empId,newShiftId,cs,ce);
                            if(result.equals("sameShiftRange")){
                                response.getWriter().write("sameShiftRange");
                            }else if(result.equals("crossover")){
                                response.getWriter().write("crossover");
                            }
                        }

                    }
                }
                //delete the old one and add the new one to the join table for the new shift
                DBOps.removeEmpShift(oldShiftId,empId);
                DBOps.addEmpShift(newShiftId,empId);
            }

        }
    }

    public String compareDayOps(LocalDateTime newStart,LocalDateTime newEnd,LocalDateTime oldStart,LocalDateTime oldEnd){

        String check = "nothing";

        if(newStart.compareTo(oldStart)<0 && newEnd.compareTo(oldEnd)<=0){

            //update startOps in the day table
            check = "s";
        }else if(newStart.compareTo(oldStart)>=0 && newEnd.compareTo(oldEnd)>0){

            //update endOps in the day table
            check = "e";
        }else if(newStart.compareTo(oldStart)< 0 && newEnd.compareTo(oldEnd)>0){

            //update startOps and endOps in the day table
            check = "both";
        }

        return check;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
