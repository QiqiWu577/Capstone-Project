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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Administrator
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
    private Set<Shift> shiftList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private EmployeeConstraints employeeConstraints;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Notification> notificationList;

    public Employee() {
        shiftList = new HashSet<>();
        notificationList = new ArrayList<Notification>();
    }

    public Employee(Integer empid) {
        this.empid = empid;
        shiftList = new HashSet<>();
        notificationList = new ArrayList<Notification>();
    }

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

        shiftList = new HashSet<>();
        notificationList = new ArrayList<Notification>();
    }

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

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public boolean getNewHire() {
        return newHire;
    }

    public void setNewHire(boolean newHire) {
        this.newHire = newHire;
    }

    public boolean getActive() {
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

    public EmployeeConstraints getEmployeeConstraints() {
        return employeeConstraints;
    }

    public void setEmployeeConstraints(EmployeeConstraints employeeConstraints) {
        this.employeeConstraints = employeeConstraints;
    }

    @XmlTransient
    public Set<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(ArrayList<Shift> shiftList) {
        this.shiftList = new HashSet<>(shiftList);
    }

    public void setShiftList(Set<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    @XmlTransient
    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(ArrayList<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empid != null ? empid.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "data.Employee[ empid=" + empid + " ]";
    }

}
