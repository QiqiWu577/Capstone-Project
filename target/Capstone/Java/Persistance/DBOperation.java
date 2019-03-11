package Persistance;


import Model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class DBOperation {

    public void run(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println(577);
        String input1 = "2007-02-07 17:29:46.00".replace( " " , "T" ) ;
        String input2 = "2008-02-07 17:29:46.00".replace( " " , "T" ) ;
        LocalDateTime dt1 = LocalDateTime.parse( input1 ) ;
        LocalDateTime dt2 = LocalDateTime.parse( input2 ) ;

        session.beginTransaction();
        //session.save(new Day(dt1,dt2));
        session.getTransaction().commit();
        session.close();
    }


    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> empList = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.close();

        return empList;

    }


    public void addEmployee(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        session.close();

    }


    public void updateEmployee(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(e);
        session.getTransaction().commit();
        session.close();
    }


    public void addShiftTemplate(ShiftTemplate st) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(st);
        session.getTransaction().commit();
        session.close();
    }


    public void addNotification(Notification n) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(n);
        session.getTransaction().commit();
        session.close();
    }

    public void updateDayTemplate(DayTemplate dt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(dt);
        session.getTransaction().commit();
        session.close();
    }


    public ArrayList<DayTemplate> getDayTemplates() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ArrayList<DayTemplate> dayTemplateList = new ArrayList<>(session.createQuery("SELECT d FROM DayTemplate d", DayTemplate.class).getResultList());
        session.getTransaction().commit();
        session.close();

        return dayTemplateList;
    }


    public ArrayList<Day> getSchedule(LocalDateTime monday) {

        monday.withHour(0);
        monday.withMinute(0);
        Date start = Date.from(monday.atZone(ZoneId.systemDefault()).toInstant());


        //set end time to a week -1 minute
        LocalDateTime endDate = monday.plusDays(6).plusHours(23).plusMinutes(59);

        Date end = Date.from(endDate.atZone(ZoneId.systemDefault()).toInstant());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT d FROM Day d WHERE d.startTime BETWEEN :start and :end");

        query.setParameter("start", start);
        query.setParameter("end", end);

        ArrayList<Day> schedule = new ArrayList<>(query.list());
        session.getTransaction().commit();

        session.close();
        return schedule;
    }


    public void updateSchedule(ArrayList<Day> schedule) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        for(Day day: schedule) {

            session.beginTransaction();
            session.update(day);
            session.getTransaction().commit();

        }

        session.close();
    }


    public void addSchedule(ArrayList<Day> schedule) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        for(Day day: schedule) {

            session.beginTransaction();
            session.save(day);
            session.getTransaction().commit();

        }
        session.close();
    }
}
