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

@WebServlet(name = "EmployeeServices", urlPatterns ="/EmployeeServices")
public class EmployeeServices extends HttpServlet {


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        DBOperation dbOps = new DBOperation();
        HttpSession session = request.getSession();

        if(page != null) {
            if(page.equals("shiftOffer")) {




                Employee emp = (Employee) session.getAttribute("employee");
                request.setAttribute("EmpShifts", emp.getShiftList());
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeShiftOffer.jsp").forward(request,response);




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
