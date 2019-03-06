package Persistance;


import org.hibernate.Session;
import org.joda.time.LocalDateTime;
import temp.Day;
import temp.Employee;


import java.util.ArrayList;


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

    public ArrayList<Day> getSchedule() {


        return null;
    }

}
