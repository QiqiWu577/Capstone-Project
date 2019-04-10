package Controllers;

import Model.*;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Jason Sy
 */
@WebServlet(name = "NotificationServices", urlPatterns ="/NotificationServices")
public class NotificationServices extends HttpServlet {
    /**
     * Controller to process requests for the maintenance and management of notifications
     *
     * If parameter is delete, remove notification from database
     * If accept, the shift sender in the notification will be swapped with the accepting recipient
     * If decline, the notification status will change to declines and saved to database
     * If send, it will determine type of notification and to whom it will be sent
     * If the employee type is manager, redirect to manager notifications page
     *
     * @param request parameter submitted from the jsp page
     * @param response system response to requests
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String delete = request.getParameter("delete");
        String accept = request.getParameter("accept");
        String decline = request.getParameter("decline");
        String send = request.getParameter("send");
        HttpSession session = request.getSession();
        DBOperation db = new DBOperation();
        Employee emp = (Employee) session.getAttribute("employee");

        /**
         *If delete was the parameter, delete the notification from the database using the note ID
         */
        if(delete!=null) {

            int noteId = Integer.parseInt(request.getParameter("noteId"));
            Notification n = db.getNotification(noteId);
            db.deleteNotification(n);

            session.setAttribute("receiveList", db.getReceivedNotifications(emp));
            session.setAttribute("sentList", db.getSentNotifications(emp));

            /**
             * If employee is manager, redirect to Manager Notifications
             */
            if(emp.getType().equals('M')) {
                session.setAttribute("manList", db.getManagerNotifications());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerNotification.jsp").forward(request,response);
            }
            else {
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request, response);
            }
        }
        if(accept!=null) {

            int noteId = Integer.parseInt(request.getParameter("noteId"));
            Notification n = db.getNotification(noteId);
            int shiftId = n.getShift_id();
            Employee sender = n.getSender();
            int recipient = n.getRecipient();

            if(sender!=null && recipient!=0) {

                Shift s = db.getShift(shiftId);
                Employee recip = db.getEmployee(recipient);

                ArrayList<Employee> list = new ArrayList<>(s.getEmployeeList());
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i).getEmpid() == sender.getEmpid()) {
                        list.set(i, recip);
                    }
                }
                s.setEmployeeList(list);

                ArrayList<Shift> senderShifts = new ArrayList<>(sender.getShiftList());
                for(int i =0; i<senderShifts.size(); i++) {
                    if(senderShifts.get(i).getShiftId() == shiftId) {
                        senderShifts.remove(i);
                    }
                }
                sender.setShiftList(senderShifts);

                ArrayList<Shift> receiveShifts = new ArrayList<>(recip.getShiftList());
                receiveShifts.add(s);
                recip.setShiftList(receiveShifts);

                db.updateEmployee(sender);
                db.updateEmployee(recip);

            }

            n.setStatus('A');
            db.updateNotification(n);
            if(db.getReceivedNotifications(emp)!=null) {
                session.setAttribute("receiveList", db.getReceivedNotifications(emp));
            }
            if(db.getSentNotifications(emp)!=null) {
                session.setAttribute("sentList", db.getSentNotifications(emp));
            }

            if(emp.getType().equals('M')) {
                session.setAttribute("manList", db.getManagerNotifications());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerNotification.jsp").forward(request,response);
            }
            else {
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request, response);
            }

        }
        if(decline!=null) {

            int noteId = Integer.parseInt(request.getParameter("noteId"));
            Notification n = db.getNotification(noteId);
            n.setStatus('D');
            db.updateNotification(n);

            if(db.getReceivedNotifications(emp)!=null) {
                session.setAttribute("receiveList", db.getReceivedNotifications(emp));
            }
            if(db.getSentNotifications(emp)!=null) {
                session.setAttribute("sentList", db.getSentNotifications(emp));
            }

            if(emp.getType().equals('M')) {

                session.setAttribute("manList", db.getManagerNotifications());
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerNotification.jsp").forward(request,response);

            }
            else {
                request.getRequestDispatcher("/WEB-INF/Presentation/Employee/EmployeeNotifications.jsp").forward(request, response);
            }

        }
        if(send!=null) {

            String comment = request.getParameter("comment");
            char type = request.getParameter("type").charAt(0);
            char status = request.getParameter("status").charAt(0);

            if(emp.getType().equals('M')) {

                String important = request.getParameter("important");

                if (important!=null) {
                    status = 'I';
                }
                else {
                    status = 'N';
                }

                if (type == 'A') {

                    ArrayList<Employee> empList = db.getEmployees();

                    for(Employee e: empList) {

                        int to = e.getEmpid();
                        Notification n = new Notification(emp,to,comment,type,status);
                        db.addNotification(n);

                    }
                } else if (type == 'D') {

                    String dept = request.getParameter("dept");

                    if (dept.equals("front")) {

                        ArrayList<Employee> empList = db.getEmployeesType('S');

                        for(Employee e: empList) {

                            int to = e.getEmpid();
                            Notification n = new Notification(emp,to,comment,type,status);
                            db.addNotification(n);

                        }

                    }
                    else if (dept.equals("bar")) {

                        ArrayList<Employee> empList = db.getEmployeesType('B');

                        for(Employee e: empList) {

                            int to = e.getEmpid();
                            Notification n = new Notification(emp,to,comment,type,status);
                            db.addNotification(n);

                        }

                    }
                    else if (dept.equals("kitchen")) {

                        ArrayList<Employee> empList = db.getEmployeesType('K');

                        for(Employee e: empList) {

                            int to = e.getEmpid();
                            Notification n = new Notification(emp,to,comment,type,status);
                            db.addNotification(n);

                        }

                    }
                } else {
                    int to = Integer.parseInt(request.getParameter("to"));
                    Notification n = new Notification(emp, to, comment, type, status);
                    db.addNotification(n);
                }
                session.setAttribute("manList", db.getManagerNotifications());

                if(db.getReceivedNotifications(emp)!=null) {
                    session.setAttribute("receiveList", db.getReceivedNotifications(emp));
                }
                if(db.getSentNotifications(emp)!=null) {
                    session.setAttribute("sentList", db.getSentNotifications(emp));
                }
                request.getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerNotification.jsp").forward(request, response);
            }
            else {

                int to = Integer.parseInt(request.getParameter("to"));
                Notification n = new Notification(emp,to,comment,type,status);
                db.addNotification(n);

                if(db.getReceivedNotifications(emp)!=null) {
                    session.setAttribute("receiveList", db.getReceivedNotifications(emp));
                }
                if(db.getSentNotifications(emp)!=null) {
                    session.setAttribute("sentList", db.getSentNotifications(emp));
                }
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
