package Persistance;

import org.hibernate.Session;
import org.hibernate.query.Query;
import temp.Day;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TestServlet",urlPatterns = "/TestServlet")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("SELECT d FROM Day d WHERE d.dayId = 1");

        List<Day> resultList = q.list();
        System.out.println("day:" + resultList.size());
        for (Day next : resultList) {
            System.out.println("next day: " + next);
        }
    }
}
