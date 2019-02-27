package Persistance;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import temp.Day;

import java.time.LocalDateTime;
import java.util.List;

public class DBOperation {

    public void run(){

        try {
            SessionFactory factory = new Configuration().configure().buildSessionFactory();

            Session session = factory.openSession();

            System.out.println(577);
            String input1 = "2007-02-07 17:29:46.00".replace( " " , "T" ) ;
            String input2 = "2008-02-07 17:29:46.00".replace( " " , "T" ) ;
            LocalDateTime dt1 = LocalDateTime.parse( input1 ) ;
            LocalDateTime dt2 = LocalDateTime.parse( input2 ) ;

            session.beginTransaction();
            session.save(new Day(dt1,dt2));
            session.getTransaction().commit();

            Day day = session.find(Day.class,1);
            System.out.println(day.getStartTime());

            Query q = session.createQuery("SELECT d FROM Day d WHERE d.dayId = 1");

            List<Day> resultList = q.list();
            System.out.println("day:" + resultList.size());
            for (Day next : resultList) {
                System.out.println("next day: " + next);
                session.close();
            }
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }




    }

}
