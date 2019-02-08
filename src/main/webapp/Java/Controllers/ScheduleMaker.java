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

    private final String filename = "./src/main/webapp/res/days.txt";
    private final String empFilename = "./src/main/webapp/res/employees.txt";
    public ScheduleMaker(int weeks) {
        this.weeks = weeks;
        getEmployees();
    }

    private void getEmployees() {

        try {
            FileReader fr = new FileReader(empFilename);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            ArrayList<Employee> empList = new ArrayList<Employee>();
            while(line != null) {

                String[] employeeData = line.split(";");
                try {
                    empList.add(new Employee(Integer.parseInt(employeeData[0]),employeeData[1],employeeData[2],employeeData[3],employeeData[4],
                            Integer.parseInt(employeeData[5]),Boolean.parseBoolean(employeeData[6]),Boolean.parseBoolean(employeeData[7]),employeeData[8],employeeData[9],null,null));
                } catch (InvalidConstraintException e) {
                    e.printStackTrace();
                } catch (ConstraintTooLongException e) {
                    e.printStackTrace();
                }
                line = reader.readLine();
            }

            for(Employee e: empList) {
                System.out.println(e.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Day> generateSchedule() {
        ArrayList<Day> schedule = new ArrayList<Day>();
        //for every day of the week starting with monday (runs 7 times)
        for(String day: days) {
            //gets all the employees availability for a given day
            sortEmployees(day);

            //gets the day and makes a day object with the opening and closing time
            DayTemplate template = getDayTemplate(day);
            Day today = new Day();
            today.setOpenTime(LocalDateTime.now().withTime(template.getOpenTime().getHourOfDay(), template.getOpenTime().getMinuteOfHour(),
                    template.getOpenTime().getSecondOfMinute(), template.getOpenTime().getMillisOfSecond()));
            today.setCloseTime(LocalDateTime.now().withTime(template.getCloseTime().getHourOfDay(), template.getCloseTime().getMinuteOfHour(),
                    template.getCloseTime().getSecondOfMinute(), template.getCloseTime().getMillisOfSecond()));

            //gets the shift templates from the day
            ArrayList<ShiftTemplate> shiftList = template.getShiftList();

            //randomizes the employees
            randomizeList();

            //for every shift that needs to be filled (runs around 3-5 times/day)
            for(ShiftTemplate shiftTemplate: shiftList) {

                //holds employees who can work but dont prefer to
                ArrayList<Employee> secondary = new ArrayList<Employee>();

                //creates a shift to be filled
                Shift shift = new Shift(shiftTemplate.getStartTime(), shiftTemplate.getEndTime(), 'S', 6);

                //for every employee in the available list of employees
                for(int j = 0; j < availList.size(); j++) {
                    boolean availShift = true;
                    boolean prefShift = false;

                    //gives the hour of the day as an int in 24 hour format eg. 11 for 11am
                    int startHour = shiftTemplate.getStartTime().getHourOfDay();
                    int endHour = shiftTemplate.getEndTime().getHourOfDay();

                    //holds the employees availability and preferences for the day
                    boolean[] currentEmpAvail = availability.get(j);
                    boolean[] currentEmpPref = null;
                    if(preferences.get(j) != null) {
                        currentEmpPref = preferences.get(j);
                        prefShift = true;
                    }

                    //Checks Availability and preferences for each shift (every hour in the shift: around 8 times/shift)
                    for(int i = startHour; i < endHour; i++) {
                        //if they can't work any hour of the shift don't schedule them
                        if(!currentEmpAvail[i]) {
                            availShift = false;
                        }
                        //if they don't prefer to work any hour in this shift don't set them as preferred
                        if(currentEmpPref != null && !currentEmpPref[i]) {
                            prefShift = false;
                        }
                    }

                    //if they are available and prefer the shift add them to the schedule
                    if(availShift && prefShift && shift.getEmpList().size() < shift.getMaximumNumberOfEmployees()) {

                        ScheduledEmployee schedEmp = new ScheduledEmployee(prefList.get(j));
                        shift.getEmpList().add(schedEmp);

                        // if they dont prefer but can work add them to the secondary list
                    } else if(availShift) {
                        secondary.add(availList.get(j));
                    }
                }

                //for every spot in the shift that still needs to be filled add from secondary (anywhere from 1-5 times)
                int x  = 0;
                for(int i = shift.getEmpList().size(); i < shift.getMinimumNumberOfEmployees(); i++) {
                    shift.getEmpList().add(new ScheduledEmployee(secondary.get(x)));
                    x++;
                }

                // if the shift had issues generating tell me
                if(shift.getEmpList().size() < shift.getMinimumNumberOfEmployees() && shift.getEmpList().size() > shift.getMaximumNumberOfEmployees()) {
                    System.out.println("Shift Generated incorrectly for shift starting at: " + shift.getStartTime() + " On: " + day);
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
