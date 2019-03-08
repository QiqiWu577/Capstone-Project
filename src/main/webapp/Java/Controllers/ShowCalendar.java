package Controllers;

import Persistance.FullcalendarDBOps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

@WebServlet(name = "ShowCalendar",urlPatterns = "/ShowCalendar")
public class ShowCalendar extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FullcalendarDBOps DBOps = new FullcalendarDBOps();
        ArrayList<CalendarDAO> list = new ArrayList();
        HttpSession session = request.getSession();

        //get the shift list from the shift table
        String shiftList = DBOps.getShifts();

        if(!shiftList.equals("")){

            String[] rows = shiftList.split(";");
            for(String row:rows){
                String[] detail = row.split(",");
                System.out.println(row);

                //set the event color
                String color = "";
                if(detail[3].equals("D")){

                    color = "green";
                }else {

                    color = "purple";
                }

                //get employee's firstName
                String fnameList = DBOps.getNameOfEmp(detail[0]);
                String[] names = fnameList.split(";");
                for(String name:names){

                    //store id,title,start,end as CalendarDAO object into the ArrayList if one on the shift or
                    // more than one employees on the same shift
                    list.add(new CalendarDAO(Integer.parseInt(detail[0]),name,detail[1],detail[2],color));

                }

            }

            //store the list as session for the use of editing
            session.setAttribute("shiftList",list);

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
