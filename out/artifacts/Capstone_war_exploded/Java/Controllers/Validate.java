package Controllers;

import Model.Day;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet(name = "Validate", urlPatterns = "/Validate")
public class Validate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean valid=false;

        PasswordManager pm = new PasswordManager();

        try {
            valid = pm.getHashSalt(Integer.parseInt(username), password);
        } catch (NumberFormatException e) {
            //Return to login page with incorrect username
        }


        String logout=request.getParameter("logout");



        if (logout!=null) {
            HttpSession session = request.getSession(false);
            session.invalidate();

            request.setAttribute("message", "Logged out");
                request.getRequestDispatcher("/index.jsp").forward(request, response);

        }
        else if (username!=null && password!=null && !username.equals("") && !password.equals("")) {

            if (valid) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);


                //if employee

                request.getRequestDispatcher("/.jsp").forward(request, response);

                //if manager

                //if admin
            }
            else {
                request.setAttribute("message", "Invalid username or password!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        }
        else {
            request.setAttribute("message", "Both username and password are required!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }






    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}