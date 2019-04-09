package Controllers;

import Model.Employee;
import Model.EmployeeConstraints;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * @author Qiqi Wu
 */
@WebServlet(name = "AdminServices",urlPatterns = "/AdminServices")
public class AdminServices extends HttpServlet {
    /**
     * Processes the request for admin navigation
     * @param request
     * @param response
     * @throws NoSuchAlgorithmException
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, ServletException, IOException {

        DBOperation dbops = new DBOperation();
        HttpSession session = request.getSession();
        String save = request.getParameter("save");
        String reset = request.getParameter("reset");
        String delete = request.getParameter("delete");
        String add = request.getParameter("add");

        if (reset != null) {

            request.setAttribute("message", "change");
            request.getRequestDispatcher("/WEB-INF/Presentation/Admin/ChangePassword.jsp").forward(request, response);

        } else {

            if (add != null) {
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String status = request.getParameter("status");
                String type = request.getParameter("type");
                boolean active = false;

                if (status.equals("Active")) {
                    active = true;
                }
                String constraints = "000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,";
                Employee emp = new Employee(0, fname, lname, "empty", "empty", "empty", type.charAt(0), true, active, "empty");
                int id = dbops.addEmployees(emp);
                EmployeeConstraints cons = new EmployeeConstraints(id,constraints,emp);
                dbops.addCons(cons);

            } else if (save != null) {

                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String[] data = name.split(" ");
                String fname = data[0];
                String lname = data[1];
                String type = request.getParameter("type");
                String status = request.getParameter("status");
                boolean active = false;

                if (status.equals("Active")) {
                    active = true;
                }

                Employee emp = new Employee(id, fname, lname, "empty", "empty", "empty", type.charAt(0), true, active, "empty");
                dbops.updateEmployee(emp);

            } else if (delete != null) {

                int id = Integer.parseInt(request.getParameter("id"));
                dbops.deleteEmp(id);
            }

            ArrayList<Employee> userList = dbops.getUsers();
           session.setAttribute("userList", userList);
            request.getRequestDispatcher("/WEB-INF/Presentation/Admin/AdminEdit.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
