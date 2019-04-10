/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anthony Doucet, Qiqi Wu
 */
@Entity
@Table(name = "employees")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
        , @NamedQuery(name = "Employee.findByEmpid", query = "SELECT e FROM Employee e WHERE e.empid = :empid")
        , @NamedQuery(name = "Employee.findByFname", query = "SELECT e FROM Employee e WHERE e.fname = :fname")
        , @NamedQuery(name = "Employee.findByLname", query = "SELECT e FROM Employee e WHERE e.lname = :lname")
        , @NamedQuery(name = "Employee.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address")
        , @NamedQuery(name = "Employee.findByPhoneno", query = "SELECT e FROM Employee e WHERE e.phoneno = :phoneno")
        , @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
        , @NamedQuery(name = "Employee.findByType", query = "SELECT e FROM Employee e WHERE e.type = :type")
        , @NamedQuery(name = "Employee.findByNewHire", query = "SELECT e FROM Employee e WHERE e.newHire = :newHire")
        , @NamedQuery(name = "Employee.findByActive", query = "SELECT e FROM Employee e WHERE e.active = :active")
        , @NamedQuery(name = "Employee.findByNotes", query = "SELECT e FROM Employee e WHERE e.notes = :notes")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer empid;
    @Basic(optional = false)
    @Column(name = "Fname")
    private String fname;
    @Basic(optional = false)
    @Column(name = "lname")
    private String lname;
    @Basic(optional = false)
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @Column(name = "Phone_no")
    private String phoneno;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "Type")
    private Character type;
    @Basic(optional = false)
    @Column(name = "newHire")
    private boolean newHire;
    @Basic(optional = false)
    @Column(name = "Active")
    private boolean active;
    @Column(name = "Notes")
    private String notes;
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "schedule_employee", joinColumns = {
            @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")}, inverseJoinColumns = {
            @JoinColumn(name = "shift_id", referencedColumnName = "shift_id")})
    private List<Shift> shiftList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private EmployeeConstraints employeeConstraints;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Notification> notificationList;

    /**
     * Initializes an Employee
     */
    public Employee() {
        shiftList = new ArrayList<Shift>();
        notificationList = new ArrayList<Notification>();
    }

    /**
     * Initializes an employee with an employee ID
      * @param empid
     */
    public Employee(Integer empid) {
        this.empid = empid;
        shiftList = new ArrayList<Shift>();
        notificationList = new ArrayList<Notification>();
    }

    /**
     * Initializes an employee with parameters
     * @param empid
     * @param address
     * @param fname
     * @param lname
     * @param phoneno
     * @param email
     * @param type
     * @param newHire
     * @param active
     * @param notes
     * @param constraints
     * @throws InvalidConstraintException
     * @throws ConstraintWrongSizeException
     */
    public Employee(Integer empid, String address, String fname, String lname, String phoneno, String email, Character type, boolean newHire, boolean active, String notes, String constraints) throws InvalidConstraintException, ConstraintWrongSizeException{
        this.empid = empid;
        this.address = address;
        this.fname = fname;
        this.lname = lname;
        this.phoneno = phoneno;
        this.email = email;
        this.type = type;
        this.newHire = newHire;
        this.active = active;
        this.notes = notes;
        employeeConstraints = new EmployeeConstraints(this, constraints, empid);

        shiftList = new ArrayList<Shift>();
        notificationList = new ArrayList<Notification>();
    }

    /**
     * Initializes an employee with parameters
     * @param empid
     * @param fname
     * @param lname
     * @param address
     * @param phoneno
     * @param email
     * @param type
     * @param newHire
     * @param active
     * @param notes
     */
    public Employee(Integer empid, String fname, String lname, String address, String phoneno, String email, Character type, boolean newHire, boolean active,String notes) {
        this.empid = empid;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.phoneno = phoneno;
        this.email = email;
        this.type = type;
        this.newHire = newHire;
        this.active = active;
        this.notes = notes;
    }

    /**
     * gets the employee id
     * @return id of the employee
     */
    public Integer getEmpid() {
        return empid;
    }

    /**
     * Sets the employee id
     * @param empid id to set
     */
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    /**
     * gets the employees address
     * @return the address of the employee
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets the employee address
     * @param address address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets the first name of the employee
     * @return first name of the employee
     */
    public String getFname() {
        return fname;
    }

    /**
     * sets the first name of the employee
     * @param fname first name of the employee
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * gets the last name of the employee
     * @return last name of the employee
     */
    public String getLname() {
        return lname;
    }

    /**
     * sets the last name of the employee
     * @param lname last name of the employee
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * gets the phone number of the employee
     * @return the employees phone number
     */
    public String getPhoneno() {
        return phoneno;
    }

    /**
     * sets the phone number of the employee
     * @param phoneno phone number of the employee
     */
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    /**
     * gets the email of the employee
     * @return the employees email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the email of the employee
     * @param email email of the employee
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets the type of the employee (Manager 'M', Admin 'A', Server 'S', Kitchen 'K', Bartender 'B')
     * @return the type of the employee
     */
    public Character getType() {
        return type;
    }

    /**
     * sets the type of the employee (Manager 'M', Admin 'A', Server 'S', Kitchen 'K', Bartender 'B')
     * @param type type of the employee
     */
    public void setType(Character type) {
        this.type = type;
    }

    /**
     * gets if the employee is a new hire
     * @return true if they're a new hire
     */
    public boolean getNewHire() {
        return newHire;
    }

    /**
     * sets if the employee is a new hire
     * @param newHire true if the employee is a new hire
     */
    public void setNewHire(boolean newHire) {
        this.newHire = newHire;
    }

    /**
     * gets if the employee is active in the system
     * @return true if the employee is active in the system
     */
    public boolean getActive() {
        return active;
    }

    /**
     * sets if the employee is active
     * @param active true if the employee is active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * gets any additional comments for the user
     * @return notes for the user
     */
    public String getNotes() {
        return notes;
    }

    /**
     * sets the notes for the employee
     * @param notes notes to set for the employee
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * gets the employee's constraints
     * @return the employees constraints
     */
    public EmployeeConstraints getEmployeeConstraints() {
        return employeeConstraints;
    }

    /**
     * sets the employees constraints
     * @param employeeConstraints employeeConstraints to set
     */
    public void setEmployeeConstraints(EmployeeConstraints employeeConstraints) {
        this.employeeConstraints = employeeConstraints;
    }

    /**
     * gets a list of the employees shifts
     * @return a list of the employees shifts
     */
    @XmlTransient
    public List<Shift> getShiftList() {
        return shiftList;
    }

    /**
     * sets the list of the employees shifts
     * @param shiftList shift list to set using an arraylist
     */
    public void setShiftList(ArrayList<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    /**
     * sets the list of the employees shifts
     * @param shiftList shift list to set using a list
     */
    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    /**
     * gets a list of the employees notifications
     * @return a list of the employees notifications
     */
    @XmlTransient
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    /**
     * sets the list of notifications
     * @param notificationList list of notifications to set
     */
    public void setNotificationList(ArrayList<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    /**
     * gets the hash code for the object
     * @return hash code of the object
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empid != null ? empid.hashCode() : 0);
        return hash;
    }

    /**
     * compares two employees checking if they're equal
     * @param object employee to compare
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.empid == null && other.empid != null) || (this.empid != null && !this.empid.equals(other.empid))) {
            return false;
        }
        return true;
    }

    /**
     * a string representation of the object
     * @return a string representation of the employee
     */
    @Override
    public String toString() {
        return "data.Employee[ empid=" + empid + " ]";
    }

}
