package Controllers.DisplayServices;

import Persistance.FullcalendarDBOps;
import com.google.gson.Gson;
import temp.CalendarDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ShowBarCalendar",urlPatterns = "/ShowBarCalendar")
public class ShowBarCalendar extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FullcalendarDBOps DBOps = new FullcalendarDBOps();
        ArrayList<CalendarDAO> list = new ArrayList();
        HttpSession session = request.getSession();
        char empType = 'B';

        //get the shift list from the shift table
        String empList = DBOps.getEmpsBT(empType);

        if(!empList.equals("")){

            String[] rows = empList.split(";");
            int i = 0;  //keep track of the index of the list for editing

            for(String row:rows){
                String[] detail = row.split(",");

                String shiftList = DBOps.getShifts(detail[0]);
                String[] shifts = shiftList.split(";");
                for(String shift:shifts){
                    String[] shiftDetail = shift.split(",");

                    //set the event color
                    String color = "";
                    if(shiftDetail[4].equals("D")){

                        color = "green";
                    }else if(shiftDetail[4].equals("M")){

                        color = "yellow";
                    }else {

                        color = "purple";
                    }

                    //store id,title,start,end,shiftID,dayId and empId as CalendarDAO object into the ArrayList if one on the shift or
                    // more than one employees on the same shift
                    list.add(new CalendarDAO(i,detail[1],shiftDetail[2],shiftDetail[3],color,Integer.parseInt(shiftDetail[0]),Integer.parseInt(shiftDetail[1]),Integer.parseInt(detail[0])));
                    i++;
                }

            }

            //store the list as session for the use of editing
            session.setAttribute("bartenderShifts",list);

            //send the generate schedule in json format to the fullcalendar
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write(new Gson().toJson(list));
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
