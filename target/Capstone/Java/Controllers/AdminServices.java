package Controllers;

import Model.ConstraintWrongSizeException;
import Model.Employee;
import Model.EmployeeConstraints;
import Model.InvalidConstraintException;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * @author Qiqi Wu, Anthony Doucet
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

        String constraints = request.getParameter("constraints");
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String position = request.getParameter("position");
        String email = request.getParameter("email");
        String comments = request.getParameter("comment");
        DBOperation dbOps = new DBOperation();
        SendEmail se = new SendEmail();
        char role;
        boolean valid = true;
        if(action != null) {


            comments = comments.replace("'", "");

            if(id == null || id.equals("") || address == null || address.equals("") || phone == null || phone.equals("")
            || fname == null || fname.equals("") || lname == null || lname.equals("") || email == null || email.equals("")
            || constraints == null || constraints.equals("")) {
                request.setAttribute("message", "All fields are required");
                request.setAttribute("employeeList", dbOps.getAllEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Admin/AdminEdit.jsp").forward(request, response);
            } else {

                if (position.equals("Bartender")) {
                    role = 'B';
                } else if (position.equals("Server")) {
                    role = 'S';
                } else if (position.equals("Kitchen")) {
                    role = 'K';
                } else if(position.equals("Admin")) {
                    role = 'A';
                } else if (position.equals("Manager")) {
                    role= 'M';
                } else {
                    role = 'X';
                }

                int numID = Integer.parseInt(id);
                if (action.equals("Save")) {
                    if (role != 'X') {
                        if (numID == 0) {
                            try {
                                int temp = dbOps.addEmployee(new Employee(numID, address, fname, lname, phone, email, role, true, true, comments, constraints));
                                se.sendEmailSingle(email, fname, temp, "new");
                                request.setAttribute("message", "Employee Added!");
                            } catch (InvalidConstraintException e) {
                                request.setAttribute("message", "Invalid Constraint!");
                                e.printStackTrace();
                            } catch (ConstraintWrongSizeException e) {
                                request.setAttribute("message", "Invalid Constraint!");
                                e.printStackTrace();
                            } finally {
                                request.setAttribute("employeeList", dbOps.getAllEmployees());
                                request.getRequestDispatcher("/WEB-INF/Presentation/Admin/AdminEdit.jsp").forward(request, response);
                            }
                        } else {
                            try {
                                dbOps.updateEmployee(new Employee(numID, address, fname, lname, phone, email, role, false, true, comments, constraints));
                                request.setAttribute("message", "Employee Updated!");
                            } catch (InvalidConstraintException e) {
                                request.setAttribute("message", "Invalid Constraint!");
                                e.printStackTrace();
                            } catch (ConstraintWrongSizeException e) {
                                request.setAttribute("message", "Invalid Constraint!");
                                e.printStackTrace();
                            } finally {
                                request.setAttribute("employeeList", dbOps.getAllEmployees());
                                request.getRequestDispatcher("/WEB-INF/Presentation/Admin/AdminEdit.jsp").forward(request, response);
                            }
                        }
                    } else {
                        request.setAttribute("message", "Invalid Position");
                        request.setAttribute("employeeList", dbOps.getAllEmployees());
                        request.getRequestDispatcher("/WEB-INF/Presentation/Admin/AdminEdit.jsp").forward(request, response);
                    }
                } else if (action.equals("Delete")) {
                    try {
                        dbOps.deleteEmployee(new Employee(numID, address, fname, lname, phone, email, role, false, false, comments, constraints));
                        request.setAttribute("message", "Employee Deleted!");
                        request.setAttribute("employeeList", dbOps.getAllEmployees());
                        request.getRequestDispatcher("/WEB-INF/Presentation/Admin/AdminEdit.jsp").forward(request, response);


                    } catch (InvalidConstraintException e) {
                        request.setAttribute("message", "Invalid Constraint!");

                    } catch (ConstraintWrongSizeException e) {
                        request.setAttribute("message", "Invalid Constraint!");

                    } finally {
                        request.setAttribute("employeeList", dbOps.getAllEmployees());
                        request.getRequestDispatcher("/WEB-INF/Presentation/Admin/AdminEdit.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("message", "Invalid Option!");
                    request.setAttribute("employeeList", dbOps.getAllEmployees());
                    request.getRequestDispatcher("/WEB-INF/Presentation/Admin/AdminEdit.jsp").forward(request, response);
                }
            }

        } else {
            request.setAttribute("employeeList", dbOps.getAllEmployees());
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
