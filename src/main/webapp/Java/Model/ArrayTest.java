package Model;

public class ArrayTest {

   //@Override
    public String toString(EmployeeConstraints ec) {


        String toParse = ec.getConstraints();

        String[] temp = toParse.split(",");

       System.out.println("Contraint Parse Test");




           System.out.println("Monday Avail:");
       for (int i=0; i<=ec.getAvailableTimeMonday().length; i++) {
           System.out.print(ec.getAvailableTimeMonday()[i]+" ");

       }

       for (int i=0; i<=24; i++) {
           System.out.print(temp[0].charAt(i));
        }

       System.out.println("Monday Pref:");
       for (int i=0; i<=ec.getPreferredTimeMonday().length; i++) {
           System.out.print(ec.getPreferredTimeMonday()[i]+" ");
       }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[1].charAt(i));
        }

        System.out.println("Tuesday Avail:");
        for (int i=0; i<=ec.getAvailableTimeTuesday().length; i++) {
            System.out.print(ec.getAvailableTimeTuesday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[2].charAt(i));
        }

        System.out.println("Tuesday Pref:");
        for (int i=0; i<=ec.getPreferredTimeTuesday().length; i++) {
            System.out.print(ec.getPreferredTimeTuesday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[3].charAt(i));
        }

        System.out.println("Wednesday Avail:");
        for (int i=0; i<=ec.getAvailableTimeWednesday().length; i++) {
            System.out.print(ec.getAvailableTimeWednesday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[4].charAt(i));
        }

        System.out.println("Wednesday Pref:");
        for (int i=0; i<=ec.getPreferredTimeWednesday().length; i++) {
            System.out.print(ec.getPreferredTimeWednesday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[5].charAt(i));
        }

        System.out.println("Thursday Avail:");
        for (int i=0; i<=ec.getAvailableTimeThursday().length; i++) {
            System.out.print(ec.getAvailableTimeThursday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[6].charAt(i));
        }

        System.out.println("Thursday Pref:");
        for (int i=0; i<=ec.getPreferredTimeThursday().length; i++) {
            System.out.print(ec.getPreferredTimeThursday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[7].charAt(i));
        }

        System.out.println("Friday Avail:");
        for (int i=0; i<=ec.getAvailableTimeFriday().length; i++) {
            System.out.print(ec.getAvailableTimeFriday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[8].charAt(i));
        }

        System.out.println("Friday Pref:");
        for (int i=0; i<=ec.getPreferredTimeFriday().length; i++) {
            System.out.print(ec.getPreferredTimeFriday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[9].charAt(i));
        }

        System.out.println("Saturday Avail:");
        for (int i=0; i<=ec.getAvailableTimeSaturday().length; i++) {
            System.out.print(ec.getAvailableTimeSaturday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[10].charAt(i));
        }

        System.out.println("Saturday Pref:");
        for (int i=0; i<=ec.getPreferredTimeSaturday().length; i++) {
            System.out.print(ec.getPreferredTimeSaturday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[11].charAt(i));
        }

        System.out.println("Sunday Avail:");
        for (int i=0; i<=ec.getAvailableTimeSunday().length; i++) {
            System.out.print(ec.getAvailableTimeSunday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[12].charAt(i));
        }

        System.out.println("Sunday Pref:");
        for (int i=0; i<=ec.getPreferredTimeSunday().length; i++) {
            System.out.print(ec.getPreferredTimeSunday()[i]+" ");
        }

        for (int i=0; i<=24; i++) {
            System.out.print(temp[13].charAt(i));
        }

       return "";

   }


}
