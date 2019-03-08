package Controllers;

import Persistance.FullcalendarDBOps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import temp.CalendarDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet(name = "EditCalendar",urlPatterns = "/EditCalendar")
public class EditCalendar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        FullcalendarDBOps DBOps = new FullcalendarDBOps();
        ArrayList<CalendarDAO> list;

        //get the shifList from the session
        //get the calendar event from the ajax function of the js
        list = (ArrayList<CalendarDAO>)session.getAttribute("shiftList");
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);
        String id= data.get("id").getAsString();
        String title = data.get("title").getAsString();
        String start = data.get("start").getAsString();
        String end = data.get("end").getAsString();

        CalendarDAO change = list.get(Integer.parseInt(id));
        int shiftId = change.getShiftId(); //used to save change into shift table
        int dayId = change.getDayId();      // used to get the operation hours and save change into day table

        //get OpsHours from day table
        String opsHours = DBOps.getOpsHours(dayId);

        String[] detail = opsHours.split(",");

        String startOps = detail[0].replace(" ","T");
        String endOps = detail[1].replace(" ","T");

        LocalDateTime s = LocalDateTime.parse(start);
        LocalDateTime e = LocalDateTime.parse(end);
        LocalDateTime sO = LocalDateTime.parse(startOps);
        LocalDateTime eO = LocalDateTime.parse(endOps);

        if(s.compareTo(sO)< 0 && e.compareTo(eO)<0){

            //update startOps in the day table
            boolean test1 = DBOps.updateDayOps(dayId,s,e,1);

            //update shift table
            boolean test2 = DBOps.updateShift(shiftId,s,e);

        }else if(s.compareTo(sO)> 0 && e.compareTo(eO)>0){

            //update endOps in the day table
            boolean test1 = DBOps.updateDayOps(dayId,s,e,2);

            //update shift table
            boolean test2 = DBOps.updateShift(shiftId,s,e);

        }else if((s.compareTo(sO)< 0 && e.compareTo(eO)>0)||(e.compareTo(sO) < 0)||(s.compareTo(eO) > 0)){

            //update startOps and endOps in the day table
            boolean test1 = DBOps.updateDayOps(dayId,s,e,3);

            //update shift table
            boolean test2 = DBOps.updateShift(shiftId,s,e);

        }else{

            //update shift table only
            if(DBOps.checkEmpShift(shiftId)>1){

                DBOps.addShift(dayId,s,e);
            }else {
                boolean test = DBOps.updateShift(shiftId,s,e);
            }

        }

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
