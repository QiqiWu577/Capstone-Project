package Controllers.EditServices;

import Persistance.FullcalendarDBOps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import temp.CalendarDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "EditSerCalendar",urlPatterns = "/EditSerCalendar")
public class EditSerCalendar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        FullcalendarDBOps DBOps = new FullcalendarDBOps();
        ArrayList<CalendarDAO> list;

        //get the shiftList from the session
        //get the calendar event from the ajax function of the js
        list = (ArrayList<CalendarDAO>)session.getAttribute("serverShifts");
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String id= data.get("id").getAsString();
        String title = data.get("title").getAsString();
        String start = data.get("start").getAsString();
        String end = data.get("end").getAsString();

        if(id != null){
            CalendarDAO change = list.get(Integer.parseInt(id));
            int shiftId = change.getShiftId(); //used to save change into shift table
            int dayId = change.getDayId();      // used to get the operation hours and save change into day table
            int empId = change.getEmpId();      //used to update the schedule_employee table for the shift

            //get OpsHours from day table
            String opsHours = DBOps.getOpsHours(dayId);

            String[] detail = opsHours.split(",");

            String startOps = detail[0].replace(" ","T");
            String endOps = detail[1].replace(" ","T");
            LocalDateTime sO = LocalDateTime.parse(startOps);
            LocalDateTime eO = LocalDateTime.parse(endOps);

            LocalDateTime s = LocalDateTime.parse(start);
            LocalDateTime e = LocalDateTime.parse(end);

            Date st = Date.from(s.atZone(ZoneId.systemDefault()).toInstant());
            Date et = Date.from(e.atZone(ZoneId.systemDefault()).toInstant());

            boolean dayAdded = false;
            //compare the date in day and shift to persist that only unique date can exist
            String differDate = compareDayOps(dayId,s,e,sO,eO);

            if(differDate.equals("different date")){

                //if there is no such that day in Day table, add day
                //if not, find that day and compare them and update the new day
                String check = DBOps.checkEmpDay(s);
                if(check.equals("0")){

                    DBOps.addDay(s,e);
                    dayAdded = true;
                }else{

                    //get the new date that already exists in the day table
                    String[] dayDetail = check.split(",");
                    dayId = Integer.parseInt(dayDetail[0]);
                    LocalDateTime newST = LocalDateTime.parse(dayDetail[1]);
                    LocalDateTime newET = LocalDateTime.parse(dayDetail[2]);
                    String condition = compareDayOps(dayId,s,e,newST,newET);
                }
            }

            //update shift table
            if(DBOps.checkEmpShift(shiftId)>1){

                if(dayAdded == true){
                    dayId = DBOps.getNewDayId(st,et);
                }
                //if there is more than one employee on the same shift,add a new shift
                //And add the new employee's shift in the join table
                DBOps.addShift(dayId,empId,s,e);

                //get shiftId of new added shift
                int newShiftId = DBOps.getNewShiftId(dayId,st,et);

                //delete the old employee's shift in the join table
                DBOps.deleteShiftJoinTable(shiftId,empId);

            }else {
                if(dayAdded == true){
                    dayId = DBOps.getNewDayId(st,et);
                }
                boolean test = DBOps.updateShift(shiftId,dayId,s,e);
            }

        }else{

            //if id = add, means user try to add a shift

            //if id = delete, means user try to delete a shift

        }
    }

    public String compareDayOps(int dayId,LocalDateTime s,LocalDateTime e,LocalDateTime sO,LocalDateTime eO){

        String check = "";
        FullcalendarDBOps DBOps = new FullcalendarDBOps();

        if(s.compareTo(sO)< 0 && e.compareTo(eO)<=0 && e.compareTo(sO)>0){

            //update startOps in the day table
            DBOps.updateDayOps(dayId,s,e,1);
            check = "update s";
        }else if(s.compareTo(sO)>= 0 && e.compareTo(eO)>0 && s.compareTo(eO)<0){

            //update endOps in the day table
            DBOps.updateDayOps(dayId,s,e,2);
            check = "update e";
        }else if(s.compareTo(sO)< 0 && e.compareTo(eO)>0){

            //update startOps and endOps in the day table
            DBOps.updateDayOps(dayId,s,e,3);
            check = "update both";
        }else if((e.compareTo(sO) < 0)||(s.compareTo(eO) > 0)){

            check = "different date";
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
