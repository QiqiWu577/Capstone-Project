package Persistance;

import org.hibernate.Session;
import org.hibernate.query.Query;
import temp.Day;
import temp.Employee;
import temp.Shift;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FullcalendarDBOps {

    public String getEmps(char empType){

        String result = "";

        String hsql = "SELECT e FROM Employee e WHERE e.type = :type";

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Query q = session.createQuery(hsql);
            q.setParameter("type", empType);

            List<Employee> empList = q.list();

            for(Employee emp : empList){

                result = result + emp.getEmpid() + "," + emp.getFname()+";";
            }

        }finally{
            session.close();
        }

        return result;
    }

    public String getShifts(String empId){

        String result = "";

        String hsql = "SELECT e.shiftList from Employee e where e.empid = :empid";

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Query q = session.createQuery(hsql);
            q.setParameter("empid",Integer.parseInt(empId));

            List<Shift> shiftList = q.list();

            for(Shift shift : shiftList){
                //shiftId,dayId,start,end,type
                result = result + shift.getShiftId() +","+shift.getDayId().getDayId()+","+shift.getStartTime()+","+shift.getEndTime()+","+shift.getShiftType()+ ";";
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

        try{

            Query q = session.createQuery(hsql);
            q.setParameter("dayId",dayId);

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

    public boolean updateShift(int shiftId, int dayId,LocalDateTime s, LocalDateTime e){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            char type = setShiftType(s,e);

            Shift shift = session.find(Shift.class,shiftId);
            Day day = new Day();
            day.setDayId(dayId);
            shift.setDayId(day);
            shift.setStartTime(s);
            shift.setEndTime(e);
            shift.setShiftType(type);
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

    public int checkEmpShift(int shiftId){

        int check = 0;

        String hsql = "SELECT s.employeeList.size from Shift s where s.shiftId = :shiftId";
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Query q = session.createQuery(hsql);
            q.setParameter("shiftId",shiftId);

            check = (Integer) q.uniqueResult();

        }finally {
            session.close();
        }

        return check;
    }

    public boolean addShift(int dayId,int empId,LocalDateTime s,LocalDateTime e){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            char type = setShiftType(s,e);
            Day day = new Day();
            day.setDayId(dayId);

            Shift shift = new Shift(day,s,e,type);

            List<Employee> empList = new ArrayList<>();
            Employee emp = new Employee();
            emp.setEmpid(empId);
            empList.add(emp);
            shift.setEmployeeList(empList);

            session.save(shift);

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

    public int getNewShiftId(int dayId,Date s,Date e){

        int result = 0;

        String hsql = "SELECT s FROM Shift s WHERE s.dayId.dayId = :dayId and s.startTime = :startTime and s.endTime = :endTime";

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Query q = session.createQuery(hsql);
            q.setParameter("dayId",dayId);
            q.setParameter("startTime",s);
            q.setParameter("endTime",e);

            Shift shifts = (Shift) q.uniqueResult();

            result = shifts.getShiftId();

        }finally{
            session.close();
        }

        return result;
    }

    public boolean deleteShiftJoinTable(int oldShiftId,int empId){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            Shift shift = session.get(Shift.class,oldShiftId);
            List<Employee> empList = shift.getEmployeeList();
            for(int i=0;i<empList.size();i++){

                if(empList.get(i).getEmpid() == empId){

                    empList.remove(i);
                }
            }

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

    public char setShiftType(LocalDateTime s,LocalDateTime e){

//        shiftType rule
//        D: s>=6:00 && e=<18:00
//        M: s=<12:00 && e>18:00
//        N: s>=18:00
        char type = ' ';
        LocalTime sT = s.toLocalTime();
        LocalTime eT = s.toLocalTime();

        String dt1 = "06:00";
        String dt2 = "12:00";
        String dt3 = "18:00";
        LocalTime six = LocalTime.parse( dt1 ) ;
        LocalTime twelve = LocalTime.parse( dt2 ) ;
        LocalTime eighteen = LocalTime.parse( dt3 ) ;

        if(sT.compareTo(six)>=0 && eT.compareTo(eighteen)<=0){
            type = 'D';
        }else if(sT.compareTo(twelve)<=0 && eT.compareTo(eighteen)>0){
            type = 'M';
        }else if(sT.compareTo(eighteen)>=0){
            type = 'N';
        }

        return type;
    }

    public String checkEmpDay(LocalDateTime s){

        String result = "0";
        String startTime = "";

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Query q = session.createQuery("SELECT d FROM Day d");
            List<Day> dayList = q.list();

            for(Day day:dayList){

                startTime = day.getStartTime();
                LocalDateTime st = LocalDateTime.parse(startTime);
                //if there is a date in the table, then we do not need to add a date
                if(s.toLocalDate().compareTo(st.toLocalDate())==0){
                    result = day.getDayId()+","+day.getStartTime()+","+day.getEndTime();
                }
            }

        }finally {
            session.close();
        }

        return result;
    }

    public boolean addDay(LocalDateTime s,LocalDateTime e){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            session.save(new Day(s,e));

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

    public int getNewDayId(Date s,Date e){

        int result = 0;

        String hsql = "SELECT d FROM Day d WHERE d.startTime = :startTime and d.endTime = :endTime";

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Query q = session.createQuery(hsql);
            q.setParameter("startTime",s);
            q.setParameter("endTime",e);

            Day day = (Day) q.uniqueResult();

            result = day.getDayId();

        }finally{
            session.close();
        }

        return result;
    }

}
