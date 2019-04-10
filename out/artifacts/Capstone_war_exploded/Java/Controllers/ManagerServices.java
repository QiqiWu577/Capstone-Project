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
import java.util.ArrayList;

/**
 * @author Jason Sy, Qiqi Wu
 */
@WebServlet(name = "ManagerServices", urlPatterns ="/ManagerServices")
public class ManagerServices extends HttpServlet {
    /**
     * Controller to process manager navigation
     *
     * If page param is settings, build arraylists and direct to settings page
     * If page param is notifications build arraylists and direct to manager notification page
     * Else direct to manager schedule view
     *
     * @param request parameter submitted from the jsp page
     * @param response system response to requests
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page = request.getParameter("page");
        DBOperation db = new DBOperation();
        HttpSession session = request.getSession();
        Employee emp = (Employee) session.getAttribute("employee");

        if(page.equals("settings")) {

            session.setAttribute("frontList", db.getShiftTemplates('S'));
            session.setAttribute("barList", db.getShiftTemplates('B'));
            session.setAttribute("kitchenList", db.getShiftTemplates('K'));

            //display operational hours from the database to the jsp table
            ArrayList<DayTemplate> list = db.getDayTemplates();
            ArrayList<DayTemplate> dayList = db.getDayTemplates();

            //order days from Monday to Sunday
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDayOfWeek().equals("Monday")) {
                    dayList.set(0, list.get(i));
                } else if (list.get(i).getDayOfWeek().equals("Tuesday")) {
                    dayList.set(1, list.get(i));
                } else if (list.get(i).getDayOfWeek().equals("Wednesday")) {
                    dayList.set(2, list.get(i));
                } else if (list.get(i).getDayOfWeek().equals("Thursday")) {
                    dayList.set(3, list.get(i));
                } else if (list.get(i).getDayOfWeek().equals("Friday")) {
                    dayList.set(4, list.get(i));
                } else if (list.get(i).getDayOfWeek().equals("Saturday")) {
                    dayList.set(5, list.get(i));
                } else if (list.get(i).getDayOfWeek().equals("Sunday")) {
                    dayList.set(6, list.get(i));
                }
            }

            session.setAttribute("dayList", dayList);
            session.setAttribute("frontList", db.getShiftTemplates('S'));
            session.setAttribute("barList", db.getShiftTemplates('B'));
            session.setAttribute("kitchenList", db.getShiftTemplates('K'));
            getServletContext().getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerSetting.jsp").forward(request, response);

        }
        else if (page.equals("notifications")){

            if(db.getManagerNotifications()!=null) {
                session.setAttribute("manList", db.getManagerNotifications());
            }
            if(db.getReceivedNotifications(emp)!=null) {
                session.setAttribute("receiveList", db.getReceivedNotifications(emp));
            }
            if(db.getSentNotifications(emp)!=null) {
                session.setAttribute("sentList", db.getSentNotifications(emp));
            }
            session.setAttribute("empList", db.getEmployees());
            request.getRequestDispatcher("/WEB-INF/Presentation/Manager/ManagerNotification.jsp").forward(request, response);

        }
        else {
            request.getRequestDispatcher("/ManageScheduleViews").forward(request, response);
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
