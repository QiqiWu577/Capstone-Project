package Controllers;

import Model.Day;

import java.util.ArrayList;

public class AppDriver {

    public static void main(String[] args) {


        ScheduleMaker sm = new ScheduleMaker(0);


        ArrayList<Day> dayList = sm.generateSchedule();



        System.out.println(dayList);



    }
}
