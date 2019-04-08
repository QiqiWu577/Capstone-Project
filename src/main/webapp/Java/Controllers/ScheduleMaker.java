package Controllers;
import Model.*;
import Persistance.DBOperation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

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


    public ScheduleMaker() {
        empList = new ArrayList<>();
        availList = new ArrayList<>();
        prefList = new ArrayList<>();
        availability = new ArrayList<>();
        preferences = new ArrayList<>();
        dayList = new ArrayList<>();
    }

    /**
     * This method will get the avail and prefer time for each employee from
     * the file and add to the empList ArrayList.
     */
    private void getEmployees(char empType) {

        empList.clear();
        DBOperation dbOps = new DBOperation();
        empList = dbOps.getEmployeesType(empType);
        for(Employee e: empList) {
            try {
                e.getEmployeeConstraints().parseConstraints();
            } catch (InvalidConstraintException e1) {
                e1.printStackTrace();
            } catch (ConstraintWrongSizeException e1) {
                e1.printStackTrace();
            }
        }
    }

    public ArrayList<Day> generateSchedule(char scheduleType) {
        getEmployees(scheduleType);

        //for every day of the week starting with monday (runs 7 times)
        boolean retryWeek = true;
        ArrayList<Day> schedule = new ArrayList<>();

        int iteration = 0;
        while(retryWeek && iteration < 200) {

            schedule = new ArrayList<>();
            LocalDateTime nextMonday = getNextMonday();
            for (String day : days) {

                boolean redoDay = true;
                int dayCount = 0;
                boolean noPref = false;



                //gets all the employees availability for a given day
                //gets the day and makes a day object with the opening and closing time
                DayTemplate template = getDayTemplate(day, scheduleType);
                Day today = new Day();


                String[] split = template.getOpenTime().split(":");


                int hour = Integer.parseInt(split[0]);
                int minute = Integer.parseInt(split[1]);
                today.setStartTime(nextMonday.withHour(hour).withMinute(minute));
                today.setDayId(0);
                split = template.getCloseTime().split(":");
                hour = Integer.parseInt(split[0]);
                minute = Integer.parseInt(split[1]);


                today.setEndTime(nextMonday.withHour(hour).withMinute(minute));

                //gets the shift templates from the day
                ArrayList<ShiftTemplate> shiftList = new ArrayList<>(template.getShiftTemplateList());

                while (redoDay && dayCount < 50) {

                    today.getShiftList().clear();
                    sortEmployees(day);
                    retryWeek = false;
                    redoDay = false;
                    printArrays();

                    //randomizes the employees
                    randomizeList();

                    //for every shift that needs to be filled (runs around 3-5 times/day)
                    for (ShiftTemplate shiftTemplate : shiftList) {

                        //holds employees who can work but dont prefer to
                        ArrayList<Employee> secondary = new ArrayList<>();
                        ArrayList<Employee> scheduled = new ArrayList<>();

                        String[] tsplit = shiftTemplate.getStartTime().split(":");

                        int shiftHour = Integer.parseInt(tsplit[0]);
                        int shiftMin = Integer.parseInt(tsplit[1]);
                        LocalDateTime shiftStartTime = nextMonday.withHour(shiftHour).withMinute(shiftMin);

                        tsplit = shiftTemplate.getEndTime().split(":");
                        shiftHour = Integer.parseInt(tsplit[0]);
                        shiftMin = Integer.parseInt(tsplit[1]);
                        LocalDateTime shiftEndTime = nextMonday.withHour(shiftHour).withMinute(shiftMin);
                        Date dStartTime = Date.from(shiftStartTime.atZone(ZoneId.systemDefault()).toInstant());
                        Date dEndTime = Date.from(shiftEndTime.atZone(ZoneId.systemDefault()).toInstant());
                        Shift shift = new Shift(0,dStartTime,dEndTime, scheduleType, shiftTemplate.getMinNoEmp(), shiftTemplate.getMaxNoEmp());
                        shift.setDayId(today);



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

                            if (availShift && prefShift && shift.getEmployeeList().size() < shift.getMaxNoEmp()) {

                                shift.getEmployeeList().add(availList.get(j));


                                List<Shift> empShiftList1 = availList.get(j).getShiftList();
                                empShiftList1.add(shift);
                                availList.get(j).setShiftList(empShiftList1);
                                scheduled.add(availList.get(j));

                            } else if (availShift) {
                                secondary.add(availList.get(j));
                            }
                        }

                        //for every spot in the shift that still needs to be filled add from secondary (anywhere from 1-5 times)
                        int x = 0;
                        for (int i = shift.getEmployeeList().size(); i < shift.getMinNoEmp(); i++) {

                            if (x < secondary.size()) {
                                shift.getEmployeeList().add(secondary.get(x));
                                List<Shift> empShiftList = secondary.get(x).getShiftList();
                                empShiftList.add(shift);
                                secondary.get(x).setShiftList(empShiftList);
                                scheduled.add(secondary.get(x));
                                x++;
                            }
                        }

                        // if the shift had issues generating tell me
                        if (shift.getEmployeeList().size() < shift.getMinNoEmp() || shift.getEmployeeList().size() > shift.getMaxNoEmp()) {
                            try {
                                throw new ShiftCannotBeFilledException(day);
                            } catch (ShiftCannotBeFilledException e) {
                                System.out.println("shift Could not be filled");
                                retryWeek = true;
                                noPref = true;
                                redoDay = true;
                            }
                        }

                        //deletes the scheduled employees from the days list of available employees once theyve been scheduled for a shift
                        for (Employee toRemove : scheduled) {

                            int index = getIndex(availList, toRemove);
                            availList.set(index, null);
                            prefList.set(index, null);
                            availability.set(index, null);
                            preferences.set(index, null);
                        }
                        today.getShiftList().add(shift);
                    }
                //while loop
                    dayCount++;
                }

                schedule.add(today);
                nextMonday = nextMonday.plusDays(1);
                System.out.println(nextMonday);
            }

            iteration++;
        }
        if(iteration >= 100) {
            System.out.println("Failed to generate schedule after 100 iterations");
        }
        return schedule;

    }


    private LocalDateTime getNextMonday() {
        DBOperation dbOps = new DBOperation();
        LocalDateTime ldt = dbOps.getLastScheduleDate();
        if(ldt.getDayOfWeek() != DayOfWeek.MONDAY) {
            ldt.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }

        return ldt;
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

    private DayTemplate getDayTemplate(String day, char type) {

        DBOperation dbOps = new DBOperation();

        for(DayTemplate dt: dbOps.getDayTemplates()) {
            if(dt.getDayOfWeek().equals(day)) {
                for(int i = 0; i < dt.getShiftTemplateList().size(); i++) {
                    if(dt.getShiftTemplateList().get(i).getType() != type) {
                        dt.getShiftTemplateList().remove(i);
                    }
                }

                return dt;
            }
        }
        return null;


    }

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
