package Persistance;

import org.hibernate.Session;
import org.hibernate.query.Query;
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

                result = result + shift.getShiftId() + "," + shift.getEmpId().getEmpid() + "," + shift.getStartTime() + "," + shift.getEndTime() + "," + shift.getShiftType() + ";";
            }

        }finally{
            session.close();
        }

        return result;
    }

    public String getNameOfEmp(String empId){

        String result = "";

        String hsql = "SELECT e FROM Employee e WHERE e.empid = :empid";

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery(hsql);
        q.setParameter("empid",Integer.parseInt(empId));

        try{

            Employee emp = (Employee)q.uniqueResult();

            result = emp.getFname();


        }finally{
            session.close();
        }

        return result;
    }
}
