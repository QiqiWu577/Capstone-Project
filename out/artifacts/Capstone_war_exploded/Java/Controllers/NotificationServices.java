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
import java.time.LocalDate;
import java.util.Date;

@WebServlet(name = "NotificationServices", urlPatterns ="/NotificationServices")
public class NotificationServices extends HttpServlet {
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String delete = request.getParameter("delete");
        String accept = request.getParameter("accept");
        String decline = request.getParameter("decline");
        String send = request.getParameter("send");
        HttpSession session = request.getSession();
        DBOperation db = new DBOperation();

        //remove after testing
        Employee e = db.getEmployee(1);
        Employee e2 = db.getEmployee(2);
        session.setAttribute("employee", e);
        //
        Employee emp = (Employee) session.getAttribute("employee");

        if(delete!=null) {

            int noteId = Integer.parseInt(request.getParameter("noteId"));
            Notification n = db.getNotification(noteId);
            db.deleteNotification(n);

            session.setAttribute("receiveList", db.getReceivedNotifications(e2));
            session.setAttribute("sentList", db.getSentNotifications(e)); //change to emp after
            request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request,response);

        }
        if(accept!=null) {

            int noteId = Integer.parseInt(request.getParameter("noteId"));
            Notification n = db.getNotification(noteId);
            n.setStatus('A');
            db.updateNotification(n);
            session.setAttribute("receiveList", db.getReceivedNotifications(e2));
            session.setAttribute("sentList", db.getSentNotifications(e)); //change to emp after
            request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request,response);

        }
        if(decline!=null) {

            int noteId = Integer.parseInt(request.getParameter("noteId"));
            Notification n = db.getNotification(noteId);
            n.setStatus('D');
            db.updateNotification(n);
            session.setAttribute("receiveList", db.getReceivedNotifications(e2));
            session.setAttribute("sentList", db.getSentNotifications(e)); //change to emp after
            request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request,response);

        }
        if(send!=null) {

            int to = Integer.parseInt(request.getParameter("to"));
            String comment = request.getParameter("comment");
            char type = request.getParameter("type").charAt(0);
            char status = request.getParameter("status").charAt(0);
            Notification n = new Notification(emp,to,comment,type,status);
            db.addNotification(n);
            session.setAttribute("receiveList", db.getReceivedNotifications(e2));
            session.setAttribute("sentList", db.getSentNotifications(e)); //change to emp after
            request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request,response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
