package Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManagerServices", urlPatterns ="/MangagerServices")
public class ManagerServices extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String newShiftDay = request.getParameter("newShiftDay");
        String message = request.getParameter("message");
        String sunday = request.getParameter("sunday");
        String monday = request.getParameter("monday");
        String tuesday = request.getParameter("tuesday");
        String wednesday = request.getParameter("wednesday");
        String thursday = request.getParameter("thursday");
        String friday = request.getParameter("friday");
        String saturday = request.getParameter("saturday");

        DBoperations db = new DBoperations();

        if (newShiftDay!=null) {

            String newShiftStart = request.getParameter("newShiftStart");
            String newShiftEnd = request.getParameter("newShiftEnd");
            //db.addShift(newShiftDay, newShiftStart, newShiftEnd);
            request.setAttribute("message", "New shift added");
            getServletContext().getRequestDispatcher("/WEB-INF/ManagerServices.jsp").forward(request, response);

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
