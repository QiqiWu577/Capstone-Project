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

@WebServlet(name = "ManagerServices", urlPatterns ="/ManagerServices")
public class ManagerServices extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String settings = request.getParameter("settings");
        String notifications = "notifications";

        DBOperation db = new DBOperation();
        HttpSession session = request.getSession();

        if(settings!=null) {

            session.setAttribute("frontList", db.getShiftTemplates('S'));
            session.setAttribute("barList", db.getShiftTemplates('B'));
            session.setAttribute("kitchenList", db.getShiftTemplates('K'));
            getServletContext().getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerSetting.jsp").forward(request, response);

        } else if (notifications!=null){
            //Employee emp = (Employee) session.getAttribute("employee");
            //delete after testing
            Employee e1 = db.getEmployee(1);
            Employee e2 = db.getEmployee(2);
            //
            session.setAttribute("manList", db.getManagerNotifications());
            session.setAttribute("receiveList", db.getReceivedNotifications(e2));//change to emp
            session.setAttribute("sentList", db.getSentNotifications(e1));//change to emp
            session.setAttribute("empList", db.getEmployees());
            request.getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerNotification.jsp").forward(request,response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
