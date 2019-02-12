package Controllers;
import Model.*;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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
    private final String empFilename = "./src/main/webapp/res/employees2.txt";

    private ArrayList<DayTemplate> dayList;


    public ScheduleMaker(int weeks) {
        this.weeks = weeks;
        empList = new ArrayList<Employee>();
        availList = new ArrayList<Employee>();
        prefList = new ArrayList<Employee>();
        availability = new ArrayList<boolean[]>();
        preferences = new ArrayList<boolean[]>();
        dayList = new ArrayList<>();
        getEmployees();


    }

    private void getEmployees() {

        try {
            this.empList.clear();
            FileReader fr = new FileReader(empFilename);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while(line != null) {

                String[] employeeData = line.split(";");
                try {
                    this.empList.add(new Employee(Integer.parseInt(employeeData[0]),employeeData[1],employeeData[2],employeeData[3],employeeData[4],
                            Integer.parseInt(employeeData[5]),Boolean.parseBoolean(employeeData[6]),Boolean.parseBoolean(employeeData[7]),employeeData[8],employeeData[9],null,null));




                } catch (InvalidConstraintException e) {
                    e.printStackTrace();
                } catch (ConstraintTooLongException e) {
                    e.printStackTrace();
                }
                line = reader.readLine();
            }

            fr.close();

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
            System.out.println(availList.size() + " " + prefList.size());
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
            System.out.println("EmpList Size" + empList.size());
            System.out.println("ShiftTemplate: " + shiftList.size());
            for(ShiftTemplate shiftTemplate: shiftList) {



                //holds employees who can work but dont prefer to
                ArrayList<Employee> secondary = new ArrayList<>();

                //creates a shift to be filled
                Shift shift = new Shift(shiftTemplate.getStartTime(), shiftTemplate.getEndTime(), 'S', 2, 1);

                //for every employee in the available list of employees
                for(int j = 0; j < availList.size(); j++) {


                    boolean availShift = true;
                    boolean prefShift = false;

                    //gives the hour of the day as an int in 24 hour format eg. 11 for 11am
                    int startHour = shiftTemplate.getStartTime().getHourOfDay();
                    int endHour = shiftTemplate.getEndTime().getHourOfDay();

                    //holds the employees availability and preferences for the day
                    boolean[] currentEmpAvail = null;
                    if(availability.get(j) != null) {
                        currentEmpAvail = availability.get(j);
                    }
                    boolean[] currentEmpPref = null;
                    if(preferences.get(j) != null) {
                        currentEmpPref = preferences.get(j);
                        prefShift = true;
                    }
                    if(availList.get(j) == null) {
                        availShift = false;
                        prefShift = false;
                    }
                    //Checks Availability and preferences for each shift (every hour in the shift: around 8 times/shift)
                    for(int i = startHour; i < endHour; i++) {



                        //if they can't work any hour of the shift don't schedule them
                        if(currentEmpAvail != null && !currentEmpAvail[i]) {
                            System.out.println("Fail");
                            availShift = false;
                        }
                        //if they don't prefer to work any hour in this shift don't set them as preferred
                        if(currentEmpPref != null && !currentEmpPref[i]) {
                            prefShift = false;
                        }
                    }

                    //if they are available and prefer the shift add them to the schedule

                    if(availShift && prefShift && shift.getEmpList().size() < shift.getMaximumNumberOfEmployees()) {
                        System.out.println("Added!");
                        if(prefList.get(j) != null && availList.get(j) != null) {
                            ScheduledEmployee schedEmp = new ScheduledEmployee(prefList.get(j));
                            shift.getEmpList().add(schedEmp);
                            availList.set(j, null);
                            prefList.set(j, null);
                            availability.set(j, null);
                            // if they dont prefer but can work add them to the secondary list
                        } else {
                            System.out.println("tried to add null");
                        }
                    } else if(availShift) {
                        System.out.println("Secondary");
                        secondary.add(availList.get(j));
                    }
                }

                //for every spot in the shift that still needs to be filled add from secondary (anywhere from 1-5 times)
                int x  = 0;
                for(int i = shift.getEmpList().size(); i < shift.getMinimumNumberOfEmployees(); i++) {
                        if(x < secondary.size()) {
                            System.out.println("Added!");
                            shift.getEmpList().add(new ScheduledEmployee(secondary.get(x)));
                            int index = getIndex(availList, secondary.get(x));
                            System.out.println(availList.size() + " " + prefList.size());
                            availList.set(index, null);
                            prefList.set(index, null);
                            availability.set(index, null);
                            x++;
                        }
                }

                // if the shift had issues generating tell me
                if(shift.getEmpList().size() < shift.getMinimumNumberOfEmployees() && shift.getEmpList().size() > shift.getMaximumNumberOfEmployees()) {
                    System.out.println("Shift Generated incorrectly for shift starting at: " + shift.getStartTime() + " On: " + day);
                }
                today.getShiftList().add(shift);
            }
            schedule.add(today);
        }
        return schedule;
    }

    private int getIndex(ArrayList<Employee> list, Employee e) {
        if(list == null) {
            System.out.println("list is null");
        }
        for(int i = 0; i< list.size(); i++) {
            if(list.get(i) != null) {
                if (e.getEmpID() == list.get(i).getEmpID()) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void randomizeList() {
        long seed = System.nanoTime();
        Collections.shuffle(availList, new Random(seed));
        Collections.shuffle(prefList, new Random(seed));
        Collections.shuffle(availability, new Random(seed));

    }

    private DayTemplate getDayTemplate(String day) {


        return loadDays(day);


    }

    private DayTemplate loadDays(String day) {
        DayTemplate dt=null;

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while(line != null) {

                String[] dayData = line.split(";");

                if (dayData[0].equalsIgnoreCase(day)) {

                    String[] shiftData = dayData[3].split("\\$");

                    ArrayList<ShiftTemplate> shiftArray = new ArrayList<ShiftTemplate>();

                    System.out.println("Length of Shift Data Array: " + shiftData.length);

                    for (int i=0; i<shiftData.length;i++){

                        String[] shiftSplitData = shiftData[i].split(",");
                        shiftArray.add(new ShiftTemplate(Integer.parseInt(shiftSplitData[0]), LocalTime.parse(shiftSplitData[1]), LocalTime.parse(shiftSplitData[2]), Integer.parseInt(shiftSplitData[3])));

                    }
                    dt = new DayTemplate(LocalTime.parse(dayData[1]), LocalTime.parse(dayData[2]),dayData[1], shiftArray);
                    dayList.add(dt);
                }

                line = reader.readLine();
            }
            fr.close();
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
        availList.clear();
        prefList.clear();
        availability.clear();
        preferences.clear();
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
                            prefList.add(null);
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
                            prefList.add(null);
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
                            prefList.add(null);
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
                            prefList.add(null);
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
                            prefList.add(null);
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
                            prefList.add(null);
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
                            prefList.add(null);
                            preferences.add(null);
                        }
                    }
                }
                break;

        }
    }


}
