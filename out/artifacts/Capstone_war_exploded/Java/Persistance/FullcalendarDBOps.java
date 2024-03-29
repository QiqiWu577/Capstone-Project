package Persistance;

import org.hibernate.Session;
import org.hibernate.query.Query;
import Model.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

/**
 * FullcalendarDBOps.java - Class describing all attributes and operations for a FullcalendarDBOps object
 * @author Qiqi Wu
 */
public class FullcalendarDBOps {

    /**
     * Method to retrieve all the information of employee object based on the employee's type
     * @param empType the type of the employee
     * @return  a string containing all the information of the employee
     */
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

    /**
     * Method to retrieve all the information of employee object based on the employee's id
     * @param empId the employee's id
     * @return a string containing all the information of the employee
     */
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

    /**
     * Method to retrieve all the shift information based on the employee's id
     * @param empId the employee's id
     * @return a string containing all the information of the shift
     */
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

    /**
     * Method to check if the shift edited is the same as the shift before editing
     * @param oldShiftId the shift before editing
     * @param cs the start time of the current shift edited
     * @param ce the end time of the current shift edited
     * @return result true or false
     */
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

    /**
     * Method to check if the day already exists in the day table
     * @param s the start time of the day
     * @return result 1 or 0. 1 means the day exists, or else it does not
     */
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

    /**
     * Method to set the shift type when adding a new shift and editing the shift
     * @param s the start time of the shift
     * @param e the end time of the shift
     * @return the type of the shift
     */
    public char setShiftType(LocalDateTime s,LocalDateTime e){

        //        shiftType rule
        //        O: s and e is not the same date
        //        D: s>=6:00 && s<=12:00 && e<18:00
        //        N: s>=16:00 && e>=18:00
        //        M: else
        char type = ' ';
        if(s.toLocalDate().compareTo(e.toLocalDate())!=0){
            type = 'O';
        }else {
            LocalTime sT = s.toLocalTime();
            LocalTime eT = s.toLocalTime();

            String dt1 = "06:00";
            String dt2 = "12:00";
            String dt3 = "16:00";
            String dt4 = "18:00";
            LocalTime six = LocalTime.parse( dt1 ) ;
            LocalTime twelve = LocalTime.parse( dt2 ) ;
            LocalTime sixteen = LocalTime.parse( dt3 ) ;
            LocalTime eighteen = LocalTime.parse( dt4 ) ;

            if(sT.compareTo(six)>=0 && sT.compareTo(twelve)<=0 && eT.compareTo(eighteen)<0){
                type = 'D';
            }else {
                type = 'N';
            }
        }

        return type;
    }

    /**
     * Method to add a new day to the day table
     * @param s the start time of the day
     * @param e the end time of the day
     * @return new day id of the new day
     */
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

    /**
     * Method to add a new shift to the shift table
     * @param dayId day id
     * @param empId employee id
     * @param s the start time of the shift
     * @param e the end time of the shift
     * @return the new shift id
     */
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

    /**
     * Method to add the shift of the specific employee into the link table
     * @param newShiftId new shift id
     * @param empId employee id
     * @return result true if adding is successful
     */
    public boolean addEmpShift(int newShiftId,int empId){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            Employee emp = session.find(Employee.class,empId);

            Shift newShift = session.find(Shift.class,newShiftId);
            List<Shift> list = emp.getShiftList();
            list.add(newShift);

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

    /**
     * Method to retrieve the operational hours of the specific day
     * @param newDayId day id
     * @return a string containing all the information of the day
     */
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

    /**
     * Method to update day table based on the conditions
     * @param newDayId day id needed to be added
     * @param s the start time of the day
     * @param e the end time of the day
     * @param condition 1 means only update the start time, 2 means only update the end time, 3 means update both of them
     * @return true if updating is successful
     */
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

    /**
     * Method to check if the shift already exists in the shift table
     * @param s the start time of the shift
     * @param e the end time of the shift
     * @return true if the shift exists
     */
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

    /**
     * Method to check if the employee has more than one shift on the same day
     * @param empId employee id
     * @param cs the start time of the shift
     * @return true if the employee does has
     */
    public boolean checkEmployeeShift(int empId,LocalDateTime cs){

        boolean result = false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Employee emp = session.find(Employee.class,empId);
            List<Shift> shiftlist = (List<Shift>) emp.getShiftList();
            for(int i=0;i<shiftlist.size();i++){

                LocalDateTime s = shiftlist.get(i).getStartTime();
                if(s.toLocalDate().compareTo(cs.toLocalDate())==0){
                    result = true;
                }
            }

        }finally {
            session.close();
        }

        return result;
    }

    /**
     * Method to check if the shifts for the specific employee does not cross over as shifts for the same employee should be separated
     * @param empId employee id
     * @param cs the start time of the shift
     * @param ce the end time of the shift
     * @return the error message if the new shift is cross over
     */
    public String checkSameEmpShift(int empId,LocalDateTime cs,LocalDateTime ce){

        String result = "";

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            Employee emp = session.find(Employee.class,empId);
            List<Shift> shiftlist = (List<Shift>) emp.getShiftList();
            for(int i=0;i<shiftlist.size();i++){

                LocalDateTime s = shiftlist.get(i).getStartTime();
                //only compare the shift on the same day
                if(s.toLocalDate().compareTo(cs.toLocalDate())==0){

                    LocalDateTime e = shiftlist.get(i).getEndTime();
                    if(cs.compareTo(s)>=0 && ce.compareTo(e)<=0){
                        result = "crossover";
                    }else if(cs.compareTo(s)<=0 && ce.compareTo(s)>0){
                        result = "crossover";
                    }else if(cs.compareTo(e)<0 && ce.compareTo(e)>=0){
                        result = "crossover";
                    }
                }
            }

        }finally {
            session.close();
        }

        return result;
    }

    /**
     * Method to check if the employee exists
     * @param employee employee's name
     * @param type the type of the employee
     * @return 1 means the employee exists
     */
    public int empExist(String employee,char type){

        int check = 0;
        String[] split = employee.split(" ");
        String fname = split[0];
        String lname = split[1];

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            Query q = session.createQuery("SELECT e FROM Employee e WHERE e.fname = :fname and e.lname = :lname and e.type = :type");
            q.setParameter("fname",fname);
            q.setParameter("lname",lname);
            q.setParameter("type",type);

            Employee emp = (Employee) q.uniqueResult();

            if(emp != null){
                check = emp.getEmpid();
            }

        }finally {
            session.close();
        }

        return check;

    }

    /**
     * Method to delete a shift of the specific employee in the link table
     * @param oldShiftId the shift id
     * @param empId the employee id
     * @return true if deleting is successful
     */
    public boolean deleteShift(int oldShiftId,int empId){

        boolean result=false;

        Session session = HibernateUtil.getSessionFactory().openSession();

        try{

            session.beginTransaction();

            Employee emp = session.find(Employee.class,empId);
            Shift shift = session.find(Shift.class,oldShiftId);
            emp.getShiftList().remove(shift);

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


}
