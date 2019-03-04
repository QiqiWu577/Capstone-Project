package Old;

import Model.Employee;
import org.joda.time.LocalTime;

import java.util.ArrayList;

public class Shift {

    private LocalTime startTime;
    private LocalTime closeTime;
    private char shiftType;
    private int minimumNumberOfEmployees;
    private int maximumNumberOfEmployees;
    private ArrayList<Employee> empList;
    private int dailyHours;

    public Shift(LocalTime startTime, LocalTime closeTime, char shiftType, int maximumNumberOfEmployees, int minimumNumberOfEmployees) {
        this.startTime = startTime;
        this.closeTime = closeTime;
        this.shiftType = shiftType;
        this.maximumNumberOfEmployees = maximumNumberOfEmployees;
        this.minimumNumberOfEmployees = minimumNumberOfEmployees;
        empList = new ArrayList<>();
    }

    public Shift(LocalTime startTime, LocalTime closeTime, char shiftType, int minimumNumberOfEmployees, int maximumNumberOfEmployees, ArrayList<Employee> empList) {
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

    public ArrayList<Employee> getEmpList() {
        return empList;
    }

    public void setEmpList(ArrayList<Employee> empList) {
        this.empList = empList;
    }
    public int getDailyHours() {
        return dailyHours;
    }
}
