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

@WebServlet(name = "Validate", urlPatterns = "/Validate")
public class Validate extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBOperation dbops = new DBOperation();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String logout = request.getParameter("logout");
        boolean valid=false;




        PasswordManager pm = new PasswordManager();




            try {
                valid = pm.getHashSalt(Integer.parseInt(username), password);
            } catch (NumberFormatException e) {
                //Return to login page with incorrect username
            }


            if (logout != null) {
                HttpSession session = request.getSession(false);
                session.invalidate();

                request.setAttribute("message", "Logged out");
                request.getRequestDispatcher("/index.jsp").forward(request, response);

            } else if (username != null && password != null && !username.equals("") && !password.equals("")) {

                boolean active = dbops.getEmployee(Integer.parseInt(username)).getActive();


                if (valid && active) {

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
