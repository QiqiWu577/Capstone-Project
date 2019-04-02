package Controllers;

<<<<<<< HEAD
import Model.Employee;
import Persistance.DBOperation;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
=======
import Model.ConstraintWrongSizeException;
import Model.Employee;
import Model.InvalidConstraintException;
import Persistance.DBOperation;
>>>>>>> 2f8f94e95d23036d2fa405e4320af1e630ae3af2

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

<<<<<<< HEAD
@WebServlet(name = "AdminServices",urlPatterns = "/AdminServices")
public class AdminServices extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, ServletException, IOException {

        DBOperation dbops = new DBOperation();
        HttpSession session = request.getSession();
        String save = request.getParameter("save");
        String reset = request.getParameter("reset");
        String delete = request.getParameter("delete");
        String add = request.getParameter("add");

        if(reset != null){

            request.setAttribute("message","change");
            request.getRequestDispatcher("/WEB-INF/Presentation/Admin/ChangePassword.jsp").forward(request,response);

        }else{

            if(add != null){
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String status = request.getParameter("status");
                String type = request.getParameter("type");
                boolean active = false;

                if(status.equals("Active")){
                    active = true;
                }

                Employee emp = new Employee(0,fname,lname,"empty","empty","empty",type.charAt(0),true,active,"empty");
                dbops.addEmployee(emp);

            }else if(save != null){

                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String[] data = name.split(" ");
                String fname = data[0];
                String lname = data[1];
                String type = request.getParameter("type");
                String status = request.getParameter("status");
                boolean active = false;

                if(status.equals("Active")){
                    active = true;
                }

                Employee emp = new Employee(id,fname,lname,"empty","empty","empty",type.charAt(0),true,active,"empty");
                dbops.updateEmployee(emp);

            }else if(delete != null){

                int id = Integer.parseInt(request.getParameter("id"));
                dbops.deleteEmp(id);
            }

            ArrayList<Employee> userList = dbops.getUsers();
            session.setAttribute("userList",userList);
            request.getRequestDispatcher("/test/Test.jsp").forward(request,response);
        }
=======
@WebServlet(name = "AdminServices", urlPatterns = "/AdminServices")
public class AdminServices extends HttpServlet {
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

        char role;
        if(action != null) {




            if(id == null ) {
                request.setAttribute("message", "Invalid ID");
                request.setAttribute("employeeList", dbOps.getAllEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
            } else if(address == null) {
                request.setAttribute("message", "Address cannot be empty");
                request.setAttribute("employeeList", dbOps.getAllEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);

            } else if(phone == null || phone.equals("")) {
                request.setAttribute("message", "Phone number cannot be empty");
                request.setAttribute("employeeList", dbOps.getAllEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);

            } else if(fname == null || fname.equals("")) {
                request.setAttribute("message", "First name cannot be empty");
                request.setAttribute("employeeList", dbOps.getAllEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);

            } else if(lname == null || lname.equals("")) {
                request.setAttribute("message", "Last name cannot be empty");
                request.setAttribute("employeeList", dbOps.getAllEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);

            } else if(email == null || email.equals("")) {
                request.setAttribute("message", "Email cannot be empty");
                request.setAttribute("employeeList", dbOps.getAllEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);

            } else if(constraints == null || constraints.equals("")) {
                request.setAttribute("message", "Invalid Constraints!");
                request.setAttribute("employeeList", dbOps.getAllEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
>>>>>>> 2f8f94e95d23036d2fa405e4320af1e630ae3af2

            } else {


                if (position.equals("Bartender")) {
                    role = 'B';
                } else if (position.equals("Server")) {
                    role = 'S';
                } else if (position.equals("Kitchen")) {
                    role = 'K';
                } else if (position.equals("Manager")) {
                    role = 'M';
                } else if (position.equals("Admin")) {
                    role = 'A';
                } else {
                    role = 'X';
                }

                int numID = Integer.parseInt(id);
                if (action.equals("Save")) {
                    if (role != 'X') {

                        if (numID == 0) {

                            try {
                                dbOps.addEmployee(new Employee(numID, address, fname, lname, phone, email, role, true, true, comments, constraints));
                                request.setAttribute("message", "Employee Added!");
                            } catch (InvalidConstraintException e) {
                                request.setAttribute("message", "Invalid Constraint!");
                                e.printStackTrace();
                            } catch (ConstraintWrongSizeException e) {
                                request.setAttribute("message", "Invalid Constraint!");
                                e.printStackTrace();
                            } finally {
                                request.setAttribute("employeeList", dbOps.getAllEmployees());
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
                                request.setAttribute("employeeList", dbOps.getAllEmployees());
                                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
                            }
                        }
                    } else {
                        request.setAttribute("message", "Invalid Position");
                        request.setAttribute("employeeList", dbOps.getAllEmployees());
                        request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
                    }
                } else if (action.equals("Delete")) {
                    try {
                        dbOps.deleteEmployee(new Employee(numID, address, fname, lname, phone, email, role, false, false, comments, constraints));
                        request.setAttribute("message", "Employee Deleted!");
                        request.setAttribute("employeeList", dbOps.getAllEmployees());
                        request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);


                    } catch (InvalidConstraintException e) {
                        request.setAttribute("message", "Invalid Constraint!");

                    } catch (ConstraintWrongSizeException e) {
                        request.setAttribute("message", "Invalid Constraint!");

                    } finally {
                        request.setAttribute("employeeList", dbOps.getAllEmployees());
                        request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("message", "Invalid Option!");
                    request.setAttribute("employeeList", dbOps.getAllEmployees());
                    request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request, response);
                }
            }

        } else {
            request.setAttribute("message", "Test");
            request.setAttribute("employeeList", dbOps.getAllEmployees());
            request.getRequestDispatcher("/WEB-INF/Presentation/Manager/EmployeeManagement.jsp").forward(request,response);
        }
    }

<<<<<<< HEAD
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
=======
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
>>>>>>> 2f8f94e95d23036d2fa405e4320af1e630ae3af2
    }
}
