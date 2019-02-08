package Controllers;
import Model.*;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ScheduleMaker {

    private ArrayList<Employee> empList;
    private ArrayList<Employee> availList;
    private ArrayList<Employee> prefList;
    private int weeks;
    private final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private ArrayList<boolean[]> availability;
    private ArrayList<boolean[]> preferences;

    private String filename = "./src/main/webapp/res/days.txt";

    public ScheduleMaker(int weeks) {
        this.weeks = weeks;
        getEmployees();
    }

    private ArrayList<Employee> getEmployees() {



        return null;
    }

    private ArrayList<Day> generateSchedule() {
        ArrayList<Day> schedule = new ArrayList<Day>();
        for(String day: days) {

            sortEmployees(day);

            DayTemplate template = getDayTemplate(day);
            Day today = new Day();
            today.setOpenTime(LocalDateTime.now().withTime(template.getOpenTime().getHourOfDay(), template.getOpenTime().getMinuteOfHour(),
                    template.getOpenTime().getSecondOfMinute(), template.getOpenTime().getMillisOfSecond()));

            today.setCloseTime(LocalDateTime.now().withTime(template.getCloseTime().getHourOfDay(), template.getCloseTime().getMinuteOfHour(),
                    template.getCloseTime().getSecondOfMinute(), template.getCloseTime().getMillisOfSecond()));

            ArrayList<ShiftTemplate> shiftList = template.getShiftList();
            randomizeList();

            for(ShiftTemplate shiftTemplate: shiftList) {

                Shift shift = new Shift(shiftTemplate.getStartTime(), shiftTemplate.getEndTime(), 'S');
                for(int j = 0; j < availList.size(); j++) {
                    boolean works = true;

                    //gives the hour of the day as an int in 24 hour format eg. 11 for 11am

                    int startHour = shiftTemplate.getStartTime().getHourOfDay();
                    int endHour = shiftTemplate.getEndTime().getHourOfDay();


                    boolean[] currentEmpAvail = availability.get(j);

                    for(int i = startHour; i < endHour; i++) {
                        if(!currentEmpAvail[i]) {
                            works = false;
                        }
                    }
                    if(works && prefList.get(j) != null) {
                        boolean prefShift = false;
                        for(int i = startHour; i < endHour; i++) {
                            prefList
                        }
                        ScheduledEmployee schedEmp = new ScheduledEmployee(prefList.get(j));
                        shift.getEmpList().add(schedEmp);



                    } else if(works) {

                    }
                }
            }

        }
        return schedule;
    }

    private void randomizeList() {
        long seed = System.nanoTime();
        Collections.shuffle(availList, new Random(seed));
        Collections.shuffle(prefList, new Random(seed));
        Collections.shuffle(availability, new Random(seed));

    }

    private DayTemplate getDayTemplate(String day) {

        DayTemplate dt=null;

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while(line != null) {

                String[] dayData = line.split(";");

                if (dayData[0].equalsIgnoreCase(day)) {

                    String[] shiftData = dayData[3].split(",");

                    ArrayList<ShiftTemplate> shiftArray = new ArrayList<ShiftTemplate>();

                    for (int i=0; i<shiftData.length;i++){

                        shiftArray.add(new ShiftTemplate(Integer.parseInt(shiftData[0]),LocalTime.parse(shiftData[1]), LocalTime.parse(shiftData[2]), Integer.parseInt(shiftData[3])));


                    }

                    dt = new DayTemplate(LocalTime.parse(dayData[1]), LocalTime.parse(dayData[2]),dayData[1], shiftArray);

                }



            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return dt;

    }
    //method to take in a day of type 'day'

    //populates the prefList and availList for a given day of the week
    private void sortEmployees(String dayOfWeek) {
        int i = 0;
        switch (dayOfWeek) {
            case "Monday":
                for(Employee e: empList) {
                    if(e.getConstraints().isAvailMonday()) {
                        availList.add(e);
                        availability.add(e.getConstraints().getAvailableTimeMonday());
                        if(e.getConstraints().isPrefMonday()) {
                            prefList.add(e);
                            preferences.add(e.getConstraints().getPreferredTimeMonday());
                        } else {
                            preferences.add(null);
                        }
                    }
                    i++;
                }
                break;
            case "Tuesday":
                for(Employee e: empList) {
                    if(e.getConstraints().isAvailTuesday()) {
                        availList.add(e);
                        availability.add(e.getConstraints().getAvailableTimeTuesday());
                        if(e.getConstraints().isPrefTuesday()) {
                            prefList.add(e);
                            preferences.add(e.getConstraints().getPreferredTimeTuesday());
                        } else {
                            preferences.add(null);
                        }
                    }
                    i++;
                }
                break;
            case "Wednesday":
                for(Employee e: empList) {
                    if(e.getConstraints().isAvailWednesday()) {
                        availList.add(e);
                        availability.add(e.getConstraints().getAvailableTimeWednesday());
                        if(e.getConstraints().isPrefWednesday()) {
                            prefList.add(e);
                            preferences.add(e.getConstraints().getPreferredTimeWednesday());
                        } else {
                            preferences.add(null);
                        }
                    }
                    i++;
                }
                break;
            case "Thursday":
                for(Employee e: empList) {
                    if(e.getConstraints().isAvailThursday()) {
                        availList.add(e);
                        availability.add(e.getConstraints().getAvailableTimeThursday());
                        if(e.getConstraints().isPrefThursday()) {
                            prefList.add(e);
                            preferences.add(e.getConstraints().getPreferredTimeThursday());
                        } else {
                            preferences.add(null);
                        }
                    }
                    i++;
                }
                break;
            case "Friday":
                for(Employee e: empList) {
                    if(e.getConstraints().isAvailFriday()) {
                        availList.add(e);
                        availability.add(e.getConstraints().getAvailableTimeFriday());
                        if(e.getConstraints().isPrefFriday()) {
                            prefList.add(e);
                            preferences.add(e.getConstraints().getPreferredTimeFriday());
                        } else {
                            preferences.add(null);
                        }
                    }
                }
                break;
            case "Saturday":
                for(Employee e: empList) {
                    if(e.getConstraints().isAvailSaturday()) {
                        availList.add(e);
                        availability.add(e.getConstraints().getAvailableTimeSaturday());
                        if(e.getConstraints().isPrefSaturday()) {
                            prefList.add(e);
                            preferences.add(e.getConstraints().getPreferredTimeSaturday());
                        } else {
                            preferences.add(null);
                        }
                    }
                }
                break;
            case "Sunday":
                for(Employee e: empList) {
                    if(e.getConstraints().isAvailSunday()) {
                        availList.add(e);
                        availability.add(e.getConstraints().getAvailableTimeSunday());
                        if(e.getConstraints().isPrefSunday()) {
                            prefList.add(e);
                            preferences.add(e.getConstraints().getPreferredTimeSunday());
                        } else {
                            preferences.add(null);
                        }
                    }
                }
                break;

        }
    }


}
