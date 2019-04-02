package Persistance;

import org.hibernate.Session;
import org.hibernate.query.Query;
import Model.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class FullcalendarDBOps {


    public String getEmpsBT(char empType){

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

    public String getEmpsBE(int empId){

        String result = "";

        String hsql = "SELECT e FROM Employee e WHERE e.empid = :empid";

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Query q = session.createQuery(hsql);
            q.setParameter("empid", empId);

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

    public boolean checkSameShift(int oldShiftId,LocalDateTime cs,LocalDateTime ce){

        boolean check = false;
        Date st = Date.from(cs.atZone(ZoneId.systemDefault()).toInstant());
        Date et = Date.from(ce.atZone(ZoneId.systemDefault()).toInstant());

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Query q = session.createQuery("select count(*) from Shift s where s.shiftId =:shiftId and s.startTime = :startTime and s.endTime = :endTime");
            q.setParameter("shiftId",oldShiftId);
            q.setParameter("startTime",st);
            q.setParameter("endTime",et);

            Long num = (Long) q.uniqueResult();

            if(num == 1){
                check = true;
            }

        }finally{
            session.close();
        }

        return check;
    }

    public int dayExist(LocalDateTime s){

        int check = 0;
        String startTime = "";

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Query q = session.createQuery("SELECT d FROM Day d");
            List<Day> dayList = q.list();

            for(Day day:dayList){

                LocalDateTime st = day.getStartTime();

                //if there is a date in the table, then we do not need to add a date
                if(s.toLocalDate().compareTo(st.toLocalDate())==0){
                    check = day.getDayId();
                }
            }

        }finally {
            session.close();
        }

        return check;
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

    public int addDay(LocalDateTime s,LocalDateTime e){

        Session session = HibernateUtil.getSessionFactory().openSession();
        int result = 0;

        try{


            session.beginTransaction();

            result = (int) session.save(new Day(s,e));

            session.getTransaction().commit();
            return result;
        }catch (Exception ex){
            session.getTransaction().rollback();
            ex.printStackTrace();
        }finally {
            session.close();
        }

        return result;
    }

    public int addShift(int dayId,int empId,LocalDateTime s,LocalDateTime e){

        int result= 0;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            char type = setShiftType(s,e);
            Day day = new Day();
            day.setDayId(dayId);

            Shift shift = new Shift(day,s,e,type);

            result = (int) session.save(shift);

            session.getTransaction().commit();

        }catch (Exception ex){
            session.getTransaction().rollback();
            ex.printStackTrace();
        }finally {
            session.close();
        }

        return result;
    }

    public boolean removeEmpShift(int oldShiftId,int empId){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            Employee emp = session.find(Employee.class,empId);
            Set set = emp.getShiftList();

            Shift oldShift = session.find(Shift.class,oldShiftId);
            set.remove(oldShift);

            session.save(emp);

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

    public boolean addEmpShift(int newShiftId,int empId){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            Employee emp = session.find(Employee.class,empId);

            Shift newShift = session.find(Shift.class,newShiftId);
            Set set = emp.getShiftList();
            set.add(newShift);

            session.save(emp);

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


    public String getOpsHours(int newDayId){

        String result = "";

        String hsql = "SELECT d FROM Day d WHERE d.dayId = :dayId";

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Query q = session.createQuery(hsql);
            q.setParameter("dayId",newDayId);

            Day day = (Day) q.uniqueResult();

            result = result + day.getStartTime() + "," + day.getEndTime();

        }finally{
            session.close();
        }

        return result;
    }

    public boolean updateDayOps(int newDayId, LocalDateTime s, LocalDateTime e, int condition){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            if(condition == 1 ){

                session.beginTransaction();

                Day day = session.find(Day.class,newDayId);
                day.setStartTime(s);
                session.update(day);

            }else if(condition == 2){

                session.beginTransaction();

                Day day = session.find(Day.class,newDayId);
                day.setEndTime(e);
                session.update(day);

            }else if(condition == 3){

                session.beginTransaction();

                Day day = session.find(Day.class,newDayId);
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

    public int shiftExist(LocalDateTime s,LocalDateTime e){

        int check = 0;
        Date st = Date.from(s.atZone(ZoneId.systemDefault()).toInstant());
        Date et = Date.from(e.atZone(ZoneId.systemDefault()).toInstant());

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Query q = session.createQuery("SELECT s FROM Shift s WHERE s.startTime = :startTime and s.endTime = :endTime");
            q.setParameter("startTime",st);
            q.setParameter("endTime",et);

            Shift shift = (Shift) q.uniqueResult();

            if(shift != null){
                check = shift.getShiftId();
            }

        }finally {
            session.close();
        }

        return check;
    }

    public boolean checkEmployeeShift(int empId,LocalDateTime cs){

        boolean result = false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Employee emp = session.find(Employee.class,empId);
            Set<Shift> shiftlist = emp.getShiftList();
            for(Shift st:shiftlist){

                LocalDateTime s = st.getStartTime();
                if(s.toLocalDate().compareTo(cs.toLocalDate())==0){
                    result = true;
                }
            }

        }finally {
            session.close();
        }

        return result;
    }

    public String checkSameShift(int empId,int newShiftId,LocalDateTime cs,LocalDateTime ce){

        String result = "";

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Employee emp = session.find(Employee.class,empId);
            Set<Shift> shiftlist = emp.getShiftList();
            for(Shift st:shiftlist){

                if(st.getShiftId() == newShiftId){
                    result = "sameShiftRange";

                }else if(!(cs.compareTo(st.getEndTime())>=0) || !(ce.compareTo(st.getStartTime())<=0)){
                    result = "crossover";
                }
            }

        }finally {
            session.close();
        }

        return result;
    }

    public int empExist(String employee){

        int check = 0;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Query q = session.createQuery("SELECT e FROM Employee e WHERE e.fname = :fname");
            q.setParameter("fname",employee);

            Employee emp = (Employee) q.uniqueResult();

            if(emp != null){
                check = emp.getEmpid();
            }

        }finally {
            session.close();
        }

        return check;

    }

    public boolean deleteShift(int empId,int oldShiftId){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            Employee emp = session.find(Employee.class,empId);
            List<Shift> shiftList = (List<Shift>) emp.getShiftList();
            for(int i=0;i<shiftList.size();i++){
                if(shiftList.get(i).getShiftId()==oldShiftId){
                    shiftList.remove(i);
                }
            }

            session.update(emp);

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
        Date startTime = Date.from(s.atZone(ZoneId.systemDefault()).toInstant());
        Date endTime = Date.from(e.atZone(ZoneId.systemDefault()).toInstant());

        try{

            session.beginTransaction();

            char type = setShiftType(s,e);

            Shift shift = session.find(Shift.class,shiftId);
            Day day = new Day();
            day.setDayId(dayId);
            shift.setDayId(day);
            shift.setStartTime(startTime);
            shift.setEndTime(endTime);
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
