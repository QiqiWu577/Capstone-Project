package Controllers;

import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;

public class AppDriver {

    public static void main(String[] args) {

       String test1;
       byte[] test2;
       boolean test3;
       String password = "password";


       PasswordManager testPM = new PasswordManager();

        try {
            test2 = testPM.getSalt();

            System.out.println(test2);

            test1 = testPM.hash(password, test2);

            System.out.println(test1);


            test3 = testPM.checkPassword(test1, "password", DatatypeConverter.printBase64Binary(test2));


            System.out.println(test3);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


//        Long start = System.currentTimeMillis();
//
//        ScheduleMaker sm = new ScheduleMaker(0);
//        ArrayList<Day> dayList = sm.generateSchedule();
//
//
//
//        System.out.println(dayList);
//        Long end = System.currentTimeMillis();
//        System.out.println("Total Time: " + (end - start));


    }
}
