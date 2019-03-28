package Persistance;


import Model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ArrayList<Employee> empList = new ArrayList<>(session.createQuery("SELECT e FROM Employee e where active = true AND type <> 'A' ", Employee.class).getResultList());
        session.getTransaction().commit();
        session.close();
        return empList;

    }

    public Employee getEmployee(int empid) {
        Employee emp = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM Employee e where e.empid=:empid", Employee.class);
        query.setParameter("empid",empid);
        session.getTransaction().commit();
        emp = (Employee) query.getSingleResult();
        session.close();

        return emp;
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


    public void deleteEmployee(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Employee SET active = false WHERE id = :id");
        query.setParameter("id",e.getEmpid());
        query.executeUpdate();

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


    public LocalDateTime getLastScheduleDate() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();


        Query query = session.createQuery("SELECT d FROM Day d WHERE d.startTime IN (select max(b.startTime) from Day b)");
        ArrayList<Day> temp = new ArrayList<>(query.list());

        session.getTransaction().commit();
        session.close();
        return temp.get(0).getStartTime();

    }

    public ArrayList<ShiftTemplate> getShiftTemplates(char type) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT s FROM ShiftTemplate s WHERE s.type = :type");
        query.setParameter("type", type);

        ArrayList<ShiftTemplate> shiftList = new ArrayList<>(query.list());
        session.getTransaction().commit();
        session.close();

        return shiftList;
    }

    public void updateShiftTemplate(ShiftTemplate st) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(st);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteShiftTemplate(ShiftTemplate st) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(st);
        session.getTransaction().commit();
        session.close();
    }

    public void addShiftTemplate1(ShiftTemplate st) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(st);
        session.getTransaction().commit();
        session.close();
    }

    public boolean updateDayTemplate(String day,String s,String e){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            DayTemplate d = session.find(DayTemplate.class,day);
            d.setOpenTime(s);
            d.setCloseTime(e);
            session.update(d);

            session.getTransaction().commit();
            result = true;
        }catch (Exception ex){
            session.getTransaction().rollback();
            ex.printStackTrace();
        }finally {
            session.close();
        }

        return result;
    }

}
