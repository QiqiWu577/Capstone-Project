package Controllers;

import Model.ConstraintWrongSizeException;
import Model.Employee;
import Model.InvalidConstraintException;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManageEmployee", urlPatterns = "/ManageEmployees")
public class ManageEmployee extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        //SendEmail se = new SendEmail();

        System.out.println(id);
        char role;
        if(action != null) {




            if(id == null ) {
                request.setAttribute("message", "Invalid ID");
                request.setAttribute("employeeList", dbOps.getEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
            } else if(address == null) {
                request.setAttribute("message", "Address cannot be empty");
                request.setAttribute("employeeList", dbOps.getEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);


            } else if(phone == null || phone.equals("")) {
                request.setAttribute("message", "Phone number cannot be empty");
                request.setAttribute("employeeList", dbOps.getEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);

            } else if(fname == null || fname.equals("")) {
                request.setAttribute("message", "First name cannot be empty");
                request.setAttribute("employeeList", dbOps.getEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);

            } else if(lname == null || lname.equals("")) {
                request.setAttribute("message", "Last name cannot be empty");
                request.setAttribute("employeeList", dbOps.getEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);

            } else if(email == null || email.equals("")) {
                request.setAttribute("message", "Email cannot be empty");
                request.setAttribute("employeeList", dbOps.getEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);

            } else if(constraints == null || constraints.equals("")) {
                request.setAttribute("message", "Invalid Constraints!");
                request.setAttribute("employeeList", dbOps.getEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);

            } else {


                if (position.equals("Bartender")) {
                    role = 'B';
                } else if (position.equals("Server")) {
                    role = 'S';
                } else if (position.equals("Kitchen")) {
                    role = 'K';
                } else {
                    role = 'X';
                }

                int numID = Integer.parseInt(id);
                if (action.equals("Save")) {
                    if (role != 'X') {

                        if (numID == 0) {

                            try {
                                dbOps.addEmployee(new Employee(numID, address, fname, lname, phone, email, role, true, true, comments, constraints));
                                //se.sendEmailSingle(email, fname, numID, "new");
                                request.setAttribute("message", "Employee Added!");
                            } catch (InvalidConstraintException e) {
                                request.setAttribute("message", "Invalid Constraint!");
                                e.printStackTrace();
                            } catch (ConstraintWrongSizeException e) {
                                request.setAttribute("message", "Invalid Constraint!");
                                e.printStackTrace();
                            } finally {
                                request.setAttribute("employeeList", dbOps.getEmployees());
                                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
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
                                request.setAttribute("employeeList", dbOps.getEmployees());
                                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
                            }
                        }
                    } else {
                        request.setAttribute("message", "Invalid Position");
                        request.setAttribute("employeeList", dbOps.getEmployees());
                        request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
                    }
                } else if (action.equals("Delete")) {
                    try {
                        dbOps.deleteEmployee(new Employee(numID, address, fname, lname, phone, email, role, false, false, comments, constraints));
                        request.setAttribute("message", "Employee Deleted!");
                        request.setAttribute("employeeList", dbOps.getEmployees());
                        request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);


                    } catch (InvalidConstraintException e) {
                        request.setAttribute("message", "Invalid Constraint!");

                    } catch (ConstraintWrongSizeException e) {
                        request.setAttribute("message", "Invalid Constraint!");

                    } finally {
                        request.setAttribute("employeeList", dbOps.getEmployees());
                        request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("message", "Invalid Option!");
                    request.setAttribute("employeeList", dbOps.getEmployees());
                    request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
                }
            }

        } else {
            request.setAttribute("message", "Test");
            request.setAttribute("employeeList", dbOps.getEmployees());
            request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
