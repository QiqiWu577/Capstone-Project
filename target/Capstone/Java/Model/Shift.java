package Model;

import org.joda.time.LocalTime;

import java.util.ArrayList;

public class Shift {

    private LocalTime startTime;
    private LocalTime closeTime;
    private char shiftType;
    private int minimumNumberOfEmployees;
    private int maximumNumberOfEmployees;
    private ArrayList<ScheduledEmployee> empList;

    public Shift(LocalTime startTime, LocalTime closeTime, char shiftType, int minimumNumberOfEmployees, int maximumNumberOfEmployees, ArrayList<ScheduledEmployee> empList) {
        this.startTime = startTime;
        this.closeTime = closeTime;
        this.shiftType = shiftType;
        this.minimumNumberOfEmployees = minimumNumberOfEmployees;
        this.maximumNumberOfEmployees = maximumNumberOfEmployees;
        this.empList = empList;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public char getShiftType() {
        return shiftType;
    }

    public void setShiftType(char shiftType) {
        this.shiftType = shiftType;
    }

    public int getMinimumNumberOfEmployees() {
        return minimumNumberOfEmployees;
    }

    public void setMinimumNumberOfEmployees(int minimumNumberOfEmployees) {
        this.minimumNumberOfEmployees = minimumNumberOfEmployees;
    }

    public int getMaximumNumberOfEmployees() {
        return maximumNumberOfEmployees;
    }

    public void setMaximumNumberOfEmployees(int maximumNumberOfEmployees) {
        this.maximumNumberOfEmployees = maximumNumberOfEmployees;
    }

    public ArrayList<ScheduledEmployee> getEmpList() {
        return empList;
    }

    public void setEmpList(ArrayList<ScheduledEmployee> empList) {
        this.empList = empList;
    }
}
