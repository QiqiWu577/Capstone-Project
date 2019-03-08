package Controllers;
import Model.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final String empFilename = "./src/main/webapp/res/employees5.txt";

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

    /**
     * This method will get the avail and prefer time for each employee from
     * the file and add to the empList ArrayList.
     */
    private void getEmployees() {

//        try {
//            this.empList.clear();
//            FileReader fr = new FileReader(empFilename);
//            BufferedReader reader = new BufferedReader(fr);
//            String line = reader.readLine();
//            while(line != null) {
//
//                String[] employeeData = line.split(";");
//                try {
//                    this.empList.add(new Employee(Integer.parseInt(employeeData[0]),employeeData[1],employeeData[2],employeeData[3],employeeData[4],
//                            Integer.parseInt(employeeData[5]),Boolean.parseBoolean(employeeData[6]),Boolean.parseBoolean(employeeData[7]),employeeData[8],employeeData[9],null,null));
//
//
//
//
//                } catch (InvalidConstraintException e) {
//                    e.printStackTrace();
//                } catch (ConstraintWrongSizeException e) {
//                    e.printStackTrace();
//                }
//                line = reader.readLine();
//            }
//
//            fr.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public ArrayList<Day> generateSchedule() {

        //for every day of the week starting with monday (runs 7 times)
        boolean retryWeek = true;
        ArrayList<Day> schedule = new ArrayList<Day>();

        int iteration = 0;

        while(retryWeek && iteration < 200) {

            schedule = new ArrayList<>();

            for (String day : days) {

                boolean redoDay = true;
                int dayCount = 0;
                boolean noPref = false;



                    //gets all the employees availability for a given day
                    //System.out.println(availList.size() + " " + prefList.size());
                    //gets the day and makes a day object with the opening and closing time
                    DayTemplate template = getDayTemplate(day);
                    Day today = new Day();


                String[] split = template.getOpenTime().split(":");


                int hour = Integer.parseInt(split[0]);
                int minute = Integer.parseInt(split[1]);

                today.setStartTime(LocalDateTime.now().withHour(hour).withMinute(minute));

                split = template.getCloseTime().split(":");
                hour = Integer.parseInt(split[0]);
                minute = Integer.parseInt(split[1]);


                today.setEndTime(LocalDateTime.now().withHour(hour).withMinute(minute));

                //gets the shift templates from the day
                ArrayList<ShiftTemplate> shiftList = new ArrayList<>(template.getShiftTemplateList());

                while (redoDay && dayCount < 50) {

                    today.getShiftList().clear();

                    System.out.println(dayCount +"-" + iteration);
                    sortEmployees(day);
                    retryWeek = false;
                    redoDay = false;


                    //randomizes the employees
                    randomizeList();

                    //for every shift that needs to be filled (runs around 3-5 times/day)
                    for (ShiftTemplate shiftTemplate : shiftList) {

                        //holds employees who can work but dont prefer to
                        ArrayList<Employee> secondary = new ArrayList<>();
                        ArrayList<Employee> scheduled = new ArrayList<>();

                        //Shift shift = new Shift(shiftTemplate.getStartTime(), shiftTemplate.getEndTime(), 'S', shiftTemplate.getMinNoEmp(), shiftTemplate.getMaxNoEmp());


                        //System.out.println("Shift " + w);
                        // w++;


                        //creates a shift to be filled

                        //for every employee in the available list of employees
                        for (int j = 0; j < availList.size(); j++) {


                            boolean availShift = true;
                            boolean prefShift = true;

                            //gives the hour of the day as an int in 24 hour format eg. 11 for 11am

                            int startHour = Integer.parseInt(shiftTemplate.getStartTime().split(":")[0]);
                            int endHour = Integer.parseInt(shiftTemplate.getEndTime().split(":")[0]);

                            //holds the employees availability and preferences for the day
                            boolean[] currentEmpAvail = null;
                            if (availability.get(j) != null) {
                                currentEmpAvail = availability.get(j);
                            }
                            boolean[] currentEmpPref = null;

                            if (preferences.get(j) == null) {
                                //System.out.println("null");
                                prefShift = false;
                            } else {
                                currentEmpPref = preferences.get(j);

                            }


                            //Checks Availability and preferences for each shift (every hour in the shift: around 8 times/shift)
                            for (int i = startHour; i < endHour; i++) {


                                //if they can't work any hour of the shift don't schedule them
                                if (currentEmpAvail != null && !currentEmpAvail[i] && availShift) {
                                    availShift = false;
                                }
                                //if they don't prefer to work any hour in this shift don't set them as preferred
                                if (currentEmpPref != null && !currentEmpPref[i] && prefShift) {
                                    // int y = 0;
                                    // for(Boolean b: currentEmpPref) {
                                    //System.out.print(y + "-" + (y+1) + " " + b + " ");
                                    //   y++;
                                    // }
                                    //System.out.println("Fail");
                                    prefShift = false;
                                }
                            }



                            if (availList.get(j) == null) {
                                availShift = false;
                                prefShift = false;
                            }
                            if (prefList.get(j) == null) {
                                prefShift = false;
                            }


                            if (noPref) {
                                prefShift = true;
                            }

                            //if they are available and prefer the shift add them to the schedule
                            //System.out.println(availShift + "-" + prefShift + "-" + shift.getEmpList().size() + "-" + shift.getMaximumNumberOfEmployees());



//                            if (availShift && prefShift && shift.getEmployeeList().size() < shift.getMaxNoEmp()) {
//                                //System.out.println("Added to shift");
//
//                                shift.getEmployeeList().add(availList.get(j));
//                                scheduled.add(availList.get(j));
//
//                                // if they dont prefer but can work add them to the secondary list
//                            } else if (availShift) {
//                                // System.out.println("Secondary to secondary list");
//                                secondary.add(availList.get(j));
//                            }
                        }

                        //for every spot in the shift that still needs to be filled add from secondary (anywhere from 1-5 times)
                        int x = 0;
//                        for (int i = shift.getEmployeeList().size(); i < shift.getMinNoEmp(); i++) {
//
//                            if (x < secondary.size()) {
//                                shift.getEmployeeList().add(secondary.get(x));
//                                scheduled.add(secondary.get(x));
//                                x++;
//                            }
//                        }
//
//                        // if the shift had issues generating tell me
//                        if (shift.getEmployeeList().size() < shift.getMinNoEmp() || shift.getEmployeeList().size() > shift.getMaxNoEmp()) {
//                            // System.out.println("Shift Generated incorrectly for shift starting at: " + shift.getStartTime() + " On: " + day);
//                            try {
//                                throw new ShiftCannotBeFilledException(day);
//                            } catch (ShiftCannotBeFilledException e) {
//                                e.printStackTrace();
//                                retryWeek = true;
//                                noPref = true;
//                                redoDay = true;
//                            }
//
//                        }

                        //deletes the scheduled employees from the days list of available employees once theyve been scheduled for a shift
                        for (Employee toRemove : scheduled) {

                            int index = getIndex(availList, toRemove);
                            availList.set(index, null);
                            prefList.set(index, null);
                            availability.set(index, null);
                            preferences.set(index, null);
                        }
                        //today.getShiftList().add(shift);
                    }
                //while loop
                    dayCount++;
                }

                schedule.add(today);

            }
            iteration++;
        }
        if(iteration >= 100) {
            System.out.println("Failed to generate schedule after 100 iterations");
        }
        return schedule;

    }

    private void printArrays() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(int i = 0; i < availList.size(); i++) {
            if(availList.get(i) != null) {
                System.out.println(availList.get(i).getFname());
            } else {
                System.out.println("Employee Null");
            }

            if(availability.get(i) != null) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }

            if(prefList.get(i) != null) {
                System.out.println(prefList.get(i).getFname());
            } else {
                System.out.println("Employee PrefList Null");
            }

            if(preferences.get(i) != null) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }
        }
    }

    private int getIndex(ArrayList<Employee> list, Employee e) {
        if(list == null) {
            System.out.println("list is null");
        }
        for(int i = 0; i< list.size(); i++) {
            if(list.get(i) != null) {
                if (e.getEmpid() == list.get(i).getEmpid()) {
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
        Collections.shuffle(preferences, new Random(seed));
    }

    private DayTemplate getDayTemplate(String day) {


        return loadDays(day);


    }

    private DayTemplate loadDays(String day) {
//        DayTemplate dt=null;
////
////        try {
////            FileReader fr = new FileReader(filename);
////            BufferedReader reader = new BufferedReader(fr);
////            String line = reader.readLine();
////            while(line != null) {
////
////                String[] dayData = line.split(";");
////
////                if (dayData[0].equalsIgnoreCase(day)) {
////
////                    String[] shiftData = dayData[3].split("\\$");
////
////                    ArrayList<ShiftTemplate> shiftArray = new ArrayList<ShiftTemplate>();
////
////                    //System.out.println("Length of Shift Data Array: " + shiftData.length);
////
////                    for (int i=0; i<shiftData.length;i++){
////
////                        String[] shiftSplitData = shiftData[i].split(",");
////                        shiftArray.add(new ShiftTemplate(Integer.parseInt(shiftSplitData[0]), LocalTime.parse(shiftSplitData[1]), LocalTime.parse(shiftSplitData[2]), Integer.parseInt(shiftSplitData[3]), 2));
////
////                    }
////                    dt = new DayTemplate(LocalTime.parse(dayData[1]), LocalTime.parse(dayData[2]),dayData[1], shiftArray);
////                    dayList.add(dt);
////                }
////
////                line = reader.readLine();
////            }
////            fr.close();
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }


        return null;
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

                    if(e.getEmployeeConstraints().isAvailMonday()) {
                        availList.add(e);
                        availability.add(e.getEmployeeConstraints().getAvailableTimeMonday());
                        if(e.getEmployeeConstraints().isPrefMonday()) {
                            prefList.add(e);
                            preferences.add(e.getEmployeeConstraints().getPreferredTimeMonday());
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

                    if(e.getEmployeeConstraints().isAvailTuesday()) {
                        availList.add(e);
                        availability.add(e.getEmployeeConstraints().getAvailableTimeTuesday());
                        if(e.getEmployeeConstraints().isPrefTuesday()) {
                            prefList.add(e);
                            preferences.add(e.getEmployeeConstraints().getPreferredTimeTuesday());
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

                    if(e.getEmployeeConstraints().isAvailWednesday()) {
                        availList.add(e);
                        availability.add(e.getEmployeeConstraints().getAvailableTimeWednesday());
                        if(e.getEmployeeConstraints().isPrefWednesday()) {
                            prefList.add(e);
                            preferences.add(e.getEmployeeConstraints().getPreferredTimeWednesday());
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

                    if(e.getEmployeeConstraints().isAvailThursday()) {
                        availList.add(e);
                        availability.add(e.getEmployeeConstraints().getAvailableTimeThursday());
                        if(e.getEmployeeConstraints().isPrefThursday()) {
                            prefList.add(e);
                            preferences.add(e.getEmployeeConstraints().getPreferredTimeThursday());
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

                    if(e.getEmployeeConstraints().isAvailFriday()) {
                        availList.add(e);
                        availability.add(e.getEmployeeConstraints().getAvailableTimeFriday());
                        if(e.getEmployeeConstraints().isPrefFriday()) {
                            prefList.add(e);
                            preferences.add(e.getEmployeeConstraints().getPreferredTimeFriday());
                        } else {
                            prefList.add(null);
                            preferences.add(null);
                        }
                    }
                }
                break;
            case "Saturday":
                for(Employee e: empList) {

                    if(e.getEmployeeConstraints().isAvailSaturday()) {
                        availList.add(e);
                        availability.add(e.getEmployeeConstraints().getAvailableTimeSaturday());
                        if(e.getEmployeeConstraints().isPrefSaturday()) {
                            prefList.add(e);
                            preferences.add(e.getEmployeeConstraints().getPreferredTimeSaturday());
                        } else {
                            prefList.add(null);
                            preferences.add(null);
                        }
                    }
                }
                break;
            case "Sunday":
                for(Employee e: empList) {

                    if(e.getEmployeeConstraints().isAvailSunday()) {
                        availList.add(e);
                        availability.add(e.getEmployeeConstraints().getAvailableTimeSunday());
                        if(e.getEmployeeConstraints().isPrefSunday()) {
                            prefList.add(e);
                            preferences.add(e.getEmployeeConstraints().getPreferredTimeSunday());
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
