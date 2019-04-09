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

/**
 * @author Jason Sy, Anthony Doucet
 */
@WebServlet(name = "EmployeeServices", urlPatterns ="/EmployeeServices")
public class EmployeeServices extends HttpServlet {

    /**
     * Processes the request for employee navigation
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = request.getParameter("page");
        DBOperation dbOps = new DBOperation();
        HttpSession session = request.getSession();
        String shiftId = request.getParameter("shiftId");
        String empId = request.getParameter("empId");

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
                    Notification notif = new Notification(emp, recipient, shift_id, content, 'S', 'W');
                    dbOps.addNotification(notif);
                    request.setAttribute("message", "Request Sent!");
                    request.setAttribute("empList", dbOps.getEmployees());
                    request.setAttribute("empShifts", emp.getShiftList());
                    request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeShiftOffer.jsp").forward(request, response);

                }

            }
            else if (page.equals("notification")) {

                if(dbOps.getReceivedNotifications(emp)!=null) {
                    session.setAttribute("receiveList", dbOps.getReceivedNotifications(emp));
                }
                if(dbOps.getSentNotifications(emp)!=null) {
                    session.setAttribute("sentList", dbOps.getSentNotifications(emp));
                }

                session.setAttribute("empList", dbOps.getEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request, response);

            }else if (page.equals("home")) {
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/ManageEmpSched.jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/ManageEmpSched.jsp").forward(request, response);
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
