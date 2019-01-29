package Model;

public class ArrayTest {

   //@Override
    public String toString(EmployeeConstraints ec) {



       System.out.println("Contraint Parse Test");

           System.out.println("Monday Avail:");
       for (int i=0; i<=ec.getAvailableTimeMonday().length; i++) {
           System.out.print(ec.getAvailableTimeMonday()[i]+" ");
       }

       System.out.println("Monday Pref:");
       for (int i=0; i<=ec.getPreferredTimeMonday().length; i++) {
           System.out.print(ec.getPreferredTimeMonday()[i]);
       }

       System.out.println("Tuesday Avail: "+ec.getAvailableTimeTuesday());
       System.out.println("Tuesday Pref: "+ec.getPreferredTimeTuesday()+"\n");

       System.out.println("Wednesday Avail: "+ec.getAvailableTimeWednesday());
       System.out.println("Wednesday Pref: "+ec.getPreferredTimeWednesday()+"\n");

       System.out.println("Thursday Avail: "+ec.getAvailableTimeThursday());
       System.out.println("Thursday Pref: "+ec.getPreferredTimeThursday()+"\n");

       System.out.println("Friday Avail: "+ec.getAvailableTimeFriday());
       System.out.println("Friday Pref: "+ec.getPreferredTimeFriday()+"\n");

       System.out.println("Saturday Avail: "+ec.getAvailableTimeSaturday());
       System.out.println("Saturday Pref: "+ec.getPreferredTimeSaturday()+"\n");

       System.out.println("Sunday Avail: "+ec.getAvailableTimeSunday());
       System.out.println("Sunday Pref: "+ec.getPreferredTimeSunday()+"\n");

       return "";

   }


}
