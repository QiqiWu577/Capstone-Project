package Controllers;

import Old.Day;

import java.util.ArrayList;

public class AppDriver {

    public static void main(String[] args) {

        Long start = System.currentTimeMillis();

        ScheduleMaker sm = new ScheduleMaker(0);
        ArrayList<Day> dayList = sm.generateSchedule();



        System.out.println(dayList);
        Long end = System.currentTimeMillis();
        System.out.println("Total Time: " + (end - start));


    }
}
