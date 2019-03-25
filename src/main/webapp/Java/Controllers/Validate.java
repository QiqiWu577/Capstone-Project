package Controllers;

import Model.Day;
import Model.Employee;
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

import static java.lang.System.currentTimeMillis;

@WebServlet(name = "Validate", urlPatterns = "/Validate")
public class Validate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBOperation dbops = new DBOperation();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean valid=false;

        long long1 = currentTimeMillis();
        long long2;

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

                Employee emp = dbops.getEmployee(Integer.parseInt(username));
                HttpSession session = request.getSession();
                session.setAttribute("employee", emp);


long2 = currentTimeMillis();
                System.out.println(long2-long1);

                if (dbops.getEmployee(Integer.parseInt(username)).getType() == 'M') {

                    request.getRequestDispatcher("/ManageEmployees").forward(request, response);


                } else if (dbops.getEmployee(Integer.parseInt(username)).getType() == 'A') {

                    request.getRequestDispatcher("/AdminServices").forward(request, response);


                } else {
                    request.getRequestDispatcher("/EmployeeServices").forward(request, response);

                }


            }
            else {

            }

        }
        else if(username == null || password == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
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
