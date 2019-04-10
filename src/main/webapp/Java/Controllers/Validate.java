package Controllers;

import Model.Employee;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Matthew Kelemen
 */
@WebServlet(name = "Validate", urlPatterns = "/Validate")
public class Validate extends HttpServlet {


    /**
     * Processes the request for user validation
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        DBOperation dbops = new DBOperation();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String logout = request.getParameter("logout");
        String action = request.getParameter("action");
        boolean valid = false;
        boolean newHire = false;


        PasswordManager pm = new PasswordManager();
        SendEmail se = new SendEmail();







        if (logout != null && !action.equals("forgot")) {

            HttpSession session = request.getSession(false);
            session.invalidate();

            request.setAttribute("message", "Logged out");
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } else if (action != null && action.equalsIgnoreCase("forgot")) {


            request.getRequestDispatcher("/WEB-INF/Presentation/Admin/ChangePassword.jsp").forward(request, response);

        }else if (action != null && action.equalsIgnoreCase("reset")) {

            Employee resetPass = dbops.getEmployee(Integer.parseInt(username));

            se.sendEmailSingle(resetPass.getEmail(), resetPass.getFname(), resetPass.getEmpid(), "reset");


        } else if (username != null && password != null && !username.equals("") && !password.equals("")) {

            try {
                valid = pm.getHashSalt(Integer.parseInt(username), password);
            } catch (NumberFormatException e) {
                //Return to login page with incorrect username
            }


            Employee loggedIn =  dbops.getEmployee(Integer.parseInt(username));

            newHire = loggedIn.getNewHire();

            boolean active = dbops.getEmployee(Integer.parseInt(username)).getActive();

            if (valid && active && newHire) {

                request.getRequestDispatcher("/ManageScheduleViews").forward(request, response);


            } else if (valid && active) {

                Employee emp = dbops.getEmployee(Integer.parseInt(username));
                HttpSession session = request.getSession();
                session.setAttribute("employee", emp);


                if (emp.getType() == 'M') {

                    request.getRequestDispatcher("/ManageScheduleViews").forward(request, response);


                } else if (emp.getType() == 'A') {

                    request.getRequestDispatcher("/AdminServices").forward(request, response);


                } else {
                    request.getRequestDispatcher("/WEB-INF/Presentation/Employee/ManageEmpSched.jsp").forward(request, response);

                }


            } else if(!active) {

                request.setAttribute("message", "Username or password is incorrect!");

                request.getRequestDispatcher("/index.jsp").forward(request, response);


            } else {
                request.setAttribute("message", "Username or password is incorrect!");

                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }

        } else if (username == null || password == null) {
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
