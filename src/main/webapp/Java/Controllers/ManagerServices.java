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

@WebServlet(name = "ManagerServices", urlPatterns ="/ManagerServices")
public class ManagerServices extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String settings = request.getParameter("settings");
        String notifications = "notifications";

        DBOperation db = new DBOperation();
        HttpSession session = request.getSession();

        if(settings!=null) {

          //display operational hours from the database to the jsp table
          ArrayList<DayTemplate> list = db.getDayTemplates();
          ArrayList<DayTemplate> dayList = db.getDayTemplates();

          //order days from Monday to Sunday
          for(int i=0;i<list.size();i++){
              if(list.get(i).getDayOfWeek().equals("Monday")){
                  dayList.set(0,list.get(i));
              }else if(list.get(i).getDayOfWeek().equals("Tuesday")){
                  dayList.set(1,list.get(i));
              }else if(list.get(i).getDayOfWeek().equals("Wednesday")){
                  dayList.set(2,list.get(i));
              }else if(list.get(i).getDayOfWeek().equals("Thursday")){
                  dayList.set(3,list.get(i));
              }else if(list.get(i).getDayOfWeek().equals("Friday")){
                  dayList.set(4,list.get(i));
              }else if(list.get(i).getDayOfWeek().equals("Saturday")){
                  dayList.set(5,list.get(i));
              }else if(list.get(i).getDayOfWeek().equals("Sunday")){
                  dayList.set(6,list.get(i));
              }

            session.setAttribute("dayList",dayList);
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
