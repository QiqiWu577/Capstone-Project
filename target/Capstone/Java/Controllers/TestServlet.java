package Controllers;

import Model.Day;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet(name = "TestServlet", urlPatterns = {"/index.html", "/login", "/show_dbs"})
public class TestServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {


        LocalDateTime monday = LocalDateTime.of(2019,03,11,0,0);
        DBOperation dbops = new DBOperation();

        ArrayList<Day> schedule = dbops.getSchedule(monday);

        for(Day day: schedule) {
            System.out.println(day.toString());
        }


        try {
            request.getRequestDispatcher("test/ManagerSchedule").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}
