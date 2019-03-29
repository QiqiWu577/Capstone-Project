package Controllers;

import Model.Employee;
import Model.Notification;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "EmployeeServices", urlPatterns ="/EmployeeServices")
public class EmployeeServices extends HttpServlet {


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        DBOperation dbOps = new DBOperation();
        HttpSession session = request.getSession();
        page = "notification";



        if(page != null) {
            if(page.equals("shiftOffer")) {

                Employee emp = (Employee) session.getAttribute("employee");
                request.setAttribute("EmpShifts", emp.getShiftList());
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeShiftOffer.jsp").forward(request,response);

            }
            if(page.equals("notification")) {

                //Employee emp = (Employee) session.getAttribute("employee");
                //delete after testing
                Employee e1 = dbOps.getEmployee(1);
                Employee e2 = dbOps.getEmployee(2);
                //
                session.setAttribute("receiveList", dbOps.getReceivedNotifications(e2));//change to emp
                session.setAttribute("sentList", dbOps.getSentNotifications(e1));//change to emp
                session.setAttribute("empList", dbOps.getEmployees());
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request,response);

            }
        } else {
            Employee emp = (Employee) session.getAttribute("employee");
            request.setAttribute("EmpShifts", emp.getShiftList());
            request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeShiftOffer.jsp").forward(request,response);
        }


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
