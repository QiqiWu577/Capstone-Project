package Persistance;

import org.hibernate.Session;

public class TestMapping {

    public static void main(String [] args){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

//        Department department = new Department("java");
//        session.save(department);
//
//        session.save(new Employee("Jakab Gipsz",department));
//        session.save(new Employee("Captain Nemo",department));

        session.getTransaction().commit();

//        Query q = session.createQuery("From Employee ");
//
//        List<Employee> resultList = q.list();
//        System.out.println("num of employess:" + resultList.size());
//        for (Employee next : resultList) {
//            System.out.println("next employee: " + next);
//        }
    }
}
