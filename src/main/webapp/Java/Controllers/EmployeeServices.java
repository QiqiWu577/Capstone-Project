package Controllers;

import Model.Employee;
import Model.Notification;
import Model.Shift;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "EmployeeServices", urlPatterns ="/EmployeeServices")
public class EmployeeServices extends HttpServlet {


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("page");
        DBOperation dbOps = new DBOperation();
        HttpSession session = request.getSession();
        String shiftId = request.getParameter("shiftId");
        String empId = request.getParameter("empId");

        page = "notification";
        Employee e1 = dbOps.getEmployee(9);
        session.setAttribute("employee", e1);

        Employee emp = (Employee) session.getAttribute("employee");


        if (page != null) {
            if (page.equals("shiftOffer")) {

                if (shiftId == null) {
                    request.setAttribute("message", "You must select a shift!");
                    request.setAttribute("empList", dbOps.getEmployees());
                    request.setAttribute("empShifts", emp.getShiftList());
                    request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeShiftOffer.jsp").forward(request, response);
                } else if (empId == null) {
                    request.setAttribute("message", "You must select an employee to trade with!");
                    request.setAttribute("empList", dbOps.getEmployees());
                    request.setAttribute("empShifts", emp.getShiftList());
                    request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeShiftOffer.jsp").forward(request, response);
                } else {

                    int recipient = Integer.parseInt(empId);
                    int shift_id = Integer.parseInt(shiftId);
                    Shift s = dbOps.getShift(shift_id);
                    String content = emp.getFname() + " want you to take their shift on " + s.getStartTime();
                    Notification notif = new Notification(emp, recipient, shift_id, content, 'S', 'A');
                    dbOps.addNotification(notif);
                    request.setAttribute("message", "Request Sent!");
                    request.setAttribute("empList", dbOps.getEmployees());
                    request.setAttribute("empShifts", emp.getShiftList());
                    request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeShiftOffer.jsp").forward(request, response);

                }

            }
            else if (page.equals("notification")) {

                session.setAttribute("receiveList", dbOps.getReceivedNotifications(emp));
                session.setAttribute("sentList", dbOps.getSentNotifications(emp));
                session.setAttribute("empList", dbOps.getEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request, response);

            }
            else {
                //this needs to be changed
                System.out.println("Test!");
                request.setAttribute("empList", dbOps.getEmployees());
                //request.setAttribute("empShifts", emp.getShiftList());
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request, response);
            }


        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

