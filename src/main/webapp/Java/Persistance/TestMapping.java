package Persistance;

import org.hibernate.Session;
import org.hibernate.query.Query;
import temp.Day;

import java.sql.Connection;
import java.util.List;

public class TestMapping {

    public static void main(String [] args){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        //Day d = new Day("2011-12-18 13:17:17","2012-12-18 13:17:17");
        //session.save(d);

        //session.save(new Employee("Jakab Gipsz",department));
//        session.save(new Employee("Captain Nemo",department));

        //session.getTransaction().commit();

        Query q = session.createQuery("From Day ");

        List<Day> resultList = q.list();
        System.out.println("day:" + resultList.size());
        for (Day next : resultList) {
            System.out.println("next day: " + next);
        }
    }
}
