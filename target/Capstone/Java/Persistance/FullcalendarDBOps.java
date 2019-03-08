package Persistance;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import temp.Day;
import temp.Employee;
import temp.Shift;

import java.time.LocalDateTime;
import java.util.List;

public class FullcalendarDBOps {

    public String getShifts(){

        String result = "";

        String hsql = "SELECT s FROM Shift s";

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery(hsql);

        try{

            List<Shift> shiftList = q.list();

            for(Shift shift : shiftList){

                result = result + shift.getShiftId() + "," +shift.getDayId().getDayId()+ "," + shift.getStartTime() + "," + shift.getEndTime() + "," + shift.getShiftType() + ";";
            }

        }finally{
            session.close();
        }

        return result;
    }

    public String getNameOfEmp(String shiftId){

        String result = "";

        String hsql = "SELECT s.employeeList from Shift s where s.shiftId = :shiftId";

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery(hsql);
        q.setParameter("shiftId",Integer.parseInt(shiftId));

        try{

            List<Employee> empList = q.list();

            for(Employee emp : empList){

                result = result + emp.getFname() + ";";
            }

        }finally{
            session.close();
        }

        return result;
    }

    public String getOpsHours(int  dayId){

        String result = "";

        String hsql = "SELECT d FROM Day d WHERE d.dayId = :dayId";

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery(hsql);
        q.setParameter("dayId",dayId);

        try{

            Day day = (Day) q.uniqueResult();

            result = result + day.getStartTime() + "," + day.getEndTime();

        }finally{
            session.close();
        }

        return result;
    }

    public boolean updateDayOps(int dayId, LocalDateTime s, LocalDateTime e, int condition){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            if(condition == 1 ){

                session.beginTransaction();

                Day day = session.find(Day.class,dayId);
                day.setStartTime(s);
                session.update(day);

            }else if(condition == 2){

                session.beginTransaction();

                Day day = session.find(Day.class,dayId);
                day.setEndTime(e);
                session.update(day);

            }else if(condition == 3){

                session.beginTransaction();

                Day day = session.find(Day.class,dayId);
                day.setStartTime(s);
                day.setEndTime(e);
                session.update(day);

            }

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

    public boolean updateShift(int shiftId, LocalDateTime s, LocalDateTime e){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            Shift shift = session.find(Shift.class,shiftId);
            shift.setStartTime(s);
            shift.setEndTime(e);
            session.update(shift);

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
