package Controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import temp.CalendarDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowCalendar",urlPatterns = "/ShowCalendar")
public class ShowCalendar extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            ArrayList<CalendarDAO> test = new ArrayList();
            String input1 = "2019-02-07 17:30:00.00".replace( " " , "T" ) ;
            String input2 = "2019-02-07 21:30:00.00".replace( " " , "T" ) ;
            String input3 = "2019-02-08 08:30:00.00".replace( " " , "T" ) ;
            String input4 = "2019-02-08 15:30:00.00".replace( " " , "T" ) ;
            LocalDateTime dt1 = LocalDateTime.parse(input1);
            LocalDateTime dt2 = LocalDateTime.parse(input2);
            LocalDateTime dt3 = LocalDateTime.parse(input3);
            LocalDateTime dt4 = LocalDateTime.parse(input4);
            test.add(new CalendarDAO(1,"Qiqi",input1,input2));
            test.add(new CalendarDAO(2,"John",input3,input4));

            //send the generate schedule in json format to the fullcalendar
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write(new Gson().toJson(test));


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
