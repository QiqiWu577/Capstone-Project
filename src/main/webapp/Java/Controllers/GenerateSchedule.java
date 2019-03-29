package Controllers;

import Model.Day;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "NotificationServices")
public class GenerateSchedule extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        DBOperation dbOps = new DBOperation();
        if(type != null) {
            ScheduleMaker scheduleMaker = new ScheduleMaker();
            ArrayList<Day> schedule;
            if(type.equals("S")) {
                schedule = scheduleMaker.generateSchedule('S');
                dbOps.addSchedule(schedule);
                request.getRequestDispatcher("/ManageScheduleViews").forward(request, response);

            } else if (type.equals("K")) {
                schedule = scheduleMaker.generateSchedule('K');
                dbOps.addSchedule(schedule);
                request.getRequestDispatcher("/ManageScheduleViews").forward(request, response);


            } else if (type.equals("B")) {
                schedule = scheduleMaker.generateSchedule('B');
                dbOps.addSchedule(schedule);
                request.getRequestDispatcher("/ManageScheduleViews").forward(request, response);


            } else {
                request.getRequestDispatcher("/ManageScheduleViews").forward(request, response);

            }
        } else {
            request.getRequestDispatcher("/ManageScheduleViews").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);

    }
}
