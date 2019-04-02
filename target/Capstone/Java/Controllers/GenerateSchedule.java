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

@WebServlet(name = "GenerateSchedule", urlPatterns = "/generateSchedule")
public class GenerateSchedule extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        DBOperation dbOps = new DBOperation();
        if(type != null) {
            ScheduleMaker scheduleMaker = new ScheduleMaker();
            ArrayList<Day> schedule;
            if(type.equals("S")) {
                System.out.println("First");
                schedule = scheduleMaker.generateSchedule('S');
                System.out.println("Second");
                for(Day d: schedule) {
                    System.out.println(d);
                }
                dbOps.addSchedule(schedule);
                System.out.println("Third");
                request.getRequestDispatcher("/ManageScheduleViews?message=server").forward(request, response);

            } else if (type.equals("K")) {
                schedule = scheduleMaker.generateSchedule('K');
                dbOps.addSchedule(schedule);
                request.getRequestDispatcher("/ManageScheduleViews?message=kitchen").forward(request, response);


            } else if (type.equals("B")) {
                schedule = scheduleMaker.generateSchedule('B');
                dbOps.addSchedule(schedule);
                request.getRequestDispatcher("/ManageScheduleViews?message=bartender").forward(request, response);


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
