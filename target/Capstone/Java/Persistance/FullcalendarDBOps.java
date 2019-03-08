package Persistance;

import org.hibernate.Session;
import org.hibernate.query.Query;
import temp.Employee;
import temp.Shift;

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

                result = result + shift.getShiftId() + "," + shift.getStartTime() + "," + shift.getEndTime() + "," + shift.getShiftType() + ";";
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
}
