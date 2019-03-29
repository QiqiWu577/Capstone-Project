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

@WebServlet(name = "EmployeeServices", urlPatterns ="/EmployeeServices")
public class EmployeeServices extends HttpServlet {


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        DBOperation dbOps = new DBOperation();
        HttpSession session = request.getSession();
        String shiftId = request.getParameter("shiftId");
        String empId = request.getParameter("empId");
        if(page != null) {
            if(page.equals("shiftOffer")) {
                Employee emp = (Employee) session.getAttribute("employee");

                if(shiftId == null) {
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
                    int sender = emp.getEmpid();
                    int recipient = Integer.parseInt(empId);




                }
            }
        } else {
            //this needs to be changed
            request.setAttribute("empList", dbOps.getEmployees());
            Employee emp = (Employee) session.getAttribute("employee");
            request.setAttribute("empShifts", emp.getShiftList());
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
