package Model;

import java.util.ArrayList;

public class Employee {

    private int empID;
    private String firstname;
    private String lastname;
    private String phoneNo;
    private String email;
    private int deptNo;
    private boolean newHire;
    private boolean active;
    private String notes;
    private EmployeeConstraints constraints;
    private ArrayList<Notification> sentNotificationsList;
    private ArrayList<Notification> recivedNotificationsList;


    public Employee(int empID, String firstname, String lastname, String phoneNo, String email, int deptNo, boolean newHire, boolean active, String notes, String constraints, ArrayList<Notification> sentNotificationsList, ArrayList<Notification> recivedNotificationsList) {
        this.empID = empID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNo = phoneNo;
        this.email = email;
        this.deptNo = deptNo;
        this.newHire = newHire;
        this.active = active;
        this.notes = notes;
        this.constraints = new EmployeeConstraints(constraints);
        this.recivedNotificationsList = recivedNotificationsList;
        this.sentNotificationsList = sentNotificationsList;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public boolean isNewHire() {
        return newHire;
    }

    public void setNewHire(boolean newHire) {
        this.newHire = newHire;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public EmployeeConstraints getConstraints() {
        return constraints;
    }

    public void setConstraints(EmployeeConstraints constraints) {
        this.constraints = constraints;
    }

    @Override
    public String toString() {
        String result = constraints.toString() + "\n" + getFirstname() + " " + getLastname() + " " + getEmail() + " " + getPhoneNo() + " " + getDeptNo();
        return result;
    }
}
