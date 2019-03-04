package Persistance;


import Model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.LocalDateTime;
import temp.Day;
<<<<<<< HEAD

=======
import test.Temp;

import java.util.ArrayList;
>>>>>>> f4119cc08143d1426368a1693aaffeb99126ba04


public class DBOperation {

    public void run(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println(577);
        String input1 = "2007-02-07 17:29:46.00".replace( " " , "T" ) ;
        String input2 = "2008-02-07 17:29:46.00".replace( " " , "T" ) ;
        LocalDateTime dt1 = LocalDateTime.parse( input1 ) ;
        LocalDateTime dt2 = LocalDateTime.parse( input2 ) ;

        session.beginTransaction();
        session.save(new Day(dt1,dt2));
        session.getTransaction().commit();
        session.close();
    }


<<<<<<< HEAD
=======
    public ArrayList<Employee> getEmployees() {

    }

    public ArrayList<Day> getSchedule() {


    }
>>>>>>> f4119cc08143d1426368a1693aaffeb99126ba04

}
