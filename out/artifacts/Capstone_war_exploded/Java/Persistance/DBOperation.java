package Persistance;


import Controllers.PasswordManager;
import Model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Anthony Doucet, Jason Sy, Qiqi Wu, Matthew Kelemen
 */
public class DBOperation {
    /**
     * gets a list of all employees
     * @return an ArrayList of all employees
     */
    public ArrayList<Employee> getAllEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ArrayList<Employee> empList = new ArrayList<>(session.createQuery("SELECT e FROM Employee e where active = true", Employee.class).getResultList());
        session.getTransaction().commit();
        session.close();
        return empList;
    }

    /**
     * Gets all employees who aren't administrators
     * @return a list of all employees except administrator
     */
    public ArrayList<Employee> getEmployees() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        ArrayList<Employee> empList = new ArrayList<>(session.createQuery("SELECT e FROM Employee e where active = true AND type <> 'A' ", Employee.class).getResultList());
        session.getTransaction().commit();
        session.close();
        return empList;

    }

    /**
     * gets a list of all employees from a specific department
     * @param type the department of the employees as a char ex 'M', 'S'
     * @return a list of all employees in that department
     */
    public ArrayList<Employee> getEmployeesType(char type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT e FROM Employee e where active = true AND type = :type ", Employee.class);
        query.setParameter("type", type);
        ArrayList<Employee> empList = new ArrayList<>(query.getResultList());
        session.getTransaction().commit();
        session.close();
        return empList;

    }

    /**
     * gets an employees based on employee ID
     * @param empid the id of the employee to find
     * @return an employee with the given ID
     */
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

    /**
     * Adds an employee to the database
     * @param e employee to add to the database
     * @return the id of the added employee
     */
    public int addEmployee(Employee e) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();


        int id = (Integer) session.save(e);
        System.out.println("TEST 3");

        session.getTransaction().commit();

        session.close();

        return id;

    }

    /**
     * updates an employee in the database
     * @param e the employee to update
     */
    public void updateEmployee(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.update(e);

        session.getTransaction().commit();

        session.close();
    }

    /**
     * logically deletes an employee
     * @param e employee to delete
     */
    public void deleteEmployee(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Employee SET active = false WHERE id = :id");
        query.setParameter("id",e.getEmpid());
        query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    /**
     * deletes an employee based on ID
     * @param id id of the employee to delete
     */
    public void deleteEmp(int id) {
        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            Employee emp = session.find(Employee.class,id);
            EmployeeConstraints cons = session.find(EmployeeConstraints.class,id);

            session.delete(cons);
            session.delete(emp);

            session.getTransaction().commit();
            result = true;
        }catch (Exception ex){
            session.getTransaction().rollback();
            ex.printStackTrace();
        }finally {
            session.close();
        }
    }

    /**
     * Adds a shift template to the database
     * @param st shift template to add
     */
    public void addShiftTemplate(ShiftTemplate st) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(st);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Adds a notification to the database
     * @param n notification to add
     */
    public void addNotification(Notification n) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(n);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * updates a day template in the database
     * @param dt day template to update
     */
    public void updateDayTemplate(DayTemplate dt) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(dt);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * gets a list of all day templates
     * @return a list of all day templates
     */
    public ArrayList<DayTemplate> getDayTemplates() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ArrayList<DayTemplate> dayTemplateList = new ArrayList<>(session.createQuery("SELECT d FROM DayTemplate d", DayTemplate.class).getResultList());
        session.getTransaction().commit();
        session.close();

        return dayTemplateList;
    }

    /**
     * gets an arralist of day objects for a week after the given day
     * @param monday the given day at the start of the week
     * @return an arraylist of days representing the schedule
     */
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

    /**
     * updates the given schedule
     * @param schedule an arraylist of days representing a schedule
     */
    public void updateSchedule(ArrayList<Day> schedule) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        for(Day day: schedule) {

            session.beginTransaction();
            session.update(day);
            session.getTransaction().commit();

        }

        session.close();
    }

    /**
     * adds a schedule to the database
     * @param schedule an arraylist of days representing a schedule
     */
    public void addSchedule(ArrayList<Day> schedule) {
        System.out.println("testSched");
        FullcalendarDBOps dbo = new FullcalendarDBOps();
        for(Day day: schedule) {
            int newDayId = dbo.addDay(day.getStartTime(), day.getEndTime());
            for(Shift s :day.getShiftList()) {
                for(Employee e: s.getEmployeeList()) {
                    int newshiftId = dbo.addShift(newDayId,e.getEmpid(),s.getStartTime(),s.getEndTime());
                    dbo.addEmpShift(newshiftId,e.getEmpid());
                }
            }
        }
    }


    /**
     * gets the data of the last day in the database
     * @return the latest date in the database
     */
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

    /**
     * updates a shift template
     * @param st the shift template to update
     */
    public void updateShiftTemplate(ShiftTemplate st) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(st);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * delets a shift template from the database
     * @param st shift template to delete
     */
    public void deleteShiftTemplate(ShiftTemplate st) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(st);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * updates a day template in the database
     * @param day day of the day template
     * @param s start time of the day template
     * @param e end time of the day template
     * @return true if added successfully
     */
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

    /**
     * gets a list of all sent notifications for the given employee
     * @param e employee to get the notifications for
     * @return a list of the employees sent notifications
     */
    public ArrayList<Notification> getSentNotifications(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT n FROM Notification n WHERE n.sender = :id ORDER BY date desc");
        query.setParameter("id", e);

        ArrayList<Notification> sentList = new ArrayList<>(query.list());
        session.getTransaction().commit();
        session.close();
        return sentList;
    }

    /**
     * gets a list of all the received notifications for the given employee
     * @param e employee to get the notifications for
     * @return the list of all received notifications
     */
    public ArrayList<Notification> getReceivedNotifications(Employee e) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT n FROM Notification n WHERE n.recipient = :id ORDER BY date desc");
        query.setParameter("id", e.getEmpid());

        ArrayList<Notification> receiveList = new ArrayList<>(query.list());
        session.getTransaction().commit();
        session.close();

        return receiveList;
    }

    /**
     * deletes a notification from the database
     * @param n notification to delete
     */
    public void deleteNotification(Notification n) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(n);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * updates a notification in the database
     * @param n notification to update
     */
    public void updateNotification(Notification n) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(n);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * gets the notification based on its ID
     * @param id id of the notification
     * @return the notification with the given ID
     */
    public Notification getNotification(int id) {
        Notification n = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT n FROM Notification n where n.notifid=:id", Notification.class);
        query.setParameter("id",id);
        session.getTransaction().commit();
        n = (Notification) query.getSingleResult();
        session.close();

        return n;
    }

    /**
     * gets all notifications for the managers
     * @return a list of all notifications for the manager
     */
    public ArrayList<Notification> getManagerNotifications() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        char p ='P';

        Query query = session.createQuery("SELECT n FROM Notification n WHERE NOT (n.notiftype = :type) ORDER BY date desc");
        query.setParameter("type", p);

        ArrayList<Notification> manList = new ArrayList<>(query.list());
        session.getTransaction().commit();
        session.close();

        return manList;
      }

    /**
     * gets a list of all shifts for an employee
     * @param empid id of the employee
     * @return the list of shifts for the given employee
     */
    public ArrayList<Shift> getShifts(int empid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //select a.firstName, a.lastName from Book b join b.authors a where b.id = :id
        Query query = session.createQuery("SELECT s FROM Employee e join e.shiftList s where e.empid = :id ");
        query.setParameter("id", empid);
        ArrayList<Shift> shiftList = new ArrayList<>(query.list());
        session.getTransaction().commit();
        session.close();
        return shiftList;

    }

    /**
     * gets the shift based on the shift ID
     * @param shiftId the ID of the shift
     * @return the shift with the given ID
     */
    public Shift getShift(int shiftId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //select a.firstName, a.lastName from Book b join b.authors a where b.id = :id
        Query query = session.createQuery("SELECT s FROM Shift s where s.shiftId = :id ");
        query.setParameter("id", shiftId);
        Shift shift = (Shift) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return shift;
    }
}
