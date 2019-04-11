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

/**
 * @author Anthony Doucet
 */
@WebServlet(name = "GenerateSchedule", urlPatterns = "/generateSchedule")
public class GenerateSchedule extends HttpServlet {
    /**
     * Processes the request for generating a new schedule based on department
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        DBOperation dbOps = new DBOperation();
        if(type != null) {
            ScheduleMaker scheduleMaker = new ScheduleMaker();
            ArrayList<Day> schedule;
            if(type.equals("S")) {
                schedule = scheduleMaker.generateSchedule('S');
                dbOps.addSchedule(schedule);
                SendEmail s = new SendEmail();
                s.sendFromGmailArray("S");
                request.getRequestDispatcher("/ManageScheduleViews?message=server").forward(request, response);

            } else if (type.equals("K")) {
                schedule = scheduleMaker.generateSchedule('K');
                dbOps.addSchedule(schedule);
                SendEmail s = new SendEmail();
                s.sendFromGmailArray("S");
                request.getRequestDispatcher("/ManageScheduleViews?message=kitchen").forward(request, response);


            } else if (type.equals("B")) {
                schedule = scheduleMaker.generateSchedule('B');
                dbOps.addSchedule(schedule);
                SendEmail s = new SendEmail();
                s.sendFromGmailArray("S");
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
