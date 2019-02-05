/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "employees")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employee e")
        , @NamedQuery(name = "Employees.findByEmpid", query = "SELECT e FROM Employee e WHERE e.empid = :empid")
        , @NamedQuery(name = "Employees.findByAddress", query = "SELECT e FROM Employee e WHERE e.address = :address")
        , @NamedQuery(name = "Employees.findByFname", query = "SELECT e FROM Employee e WHERE e.fname = :fname")
        , @NamedQuery(name = "Employees.findByLname", query = "SELECT e FROM Employee e WHERE e.lname = :lname")
        , @NamedQuery(name = "Employees.findByPhoneno", query = "SELECT e FROM Employee e WHERE e.phoneno = :phoneno")
        , @NamedQuery(name = "Employees.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
        , @NamedQuery(name = "Employees.findByType", query = "SELECT e FROM Employee e WHERE e.type = :type")
        , @NamedQuery(name = "Employees.findByNewHire", query = "SELECT e FROM Employee e WHERE e.newHire = :newHire")
        , @NamedQuery(name = "Employees.findByActive", query = "SELECT e FROM Employee e WHERE e.active = :active")
        , @NamedQuery(name = "Employees.findByNotes", query = "SELECT e FROM Employee e WHERE e.notes = :notes")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Emp_id")
    private Integer empid;

    @Basic(optional = false)
    @Column(name = "Address")
    private String address;

    @Basic(optional = false)
    @Column(name = "Fname")
    private String fname;

    @Basic(optional = false)
    @Column(name = "lname")
    private String lname;

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

    @Transient
    private EmployeeConstraints constraints;

    @Transient
    private ArrayList<Notification> sentNotificationsList;

    @Transient
    private ArrayList<Notification> recivedNotificationsList;

    @JoinTable(name = "scheduled_employees", joinColumns = {
            @JoinColumn(name = "emp_id", referencedColumnName = "Emp_id")}, inverseJoinColumns = {
            @JoinColumn(name = "shift_id", referencedColumnName = "shift_id")})
    @ManyToMany
    private List<Shift> shiftList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employees")
    private EmployeeConstraints employeeConstraints;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Notification> notificationsList;

    public Employee() {
    }

    public Employee(Integer empid) {
        this.empid = empid;
    }

    public Employee(Integer empid, String address, String fname, String lname, String phoneno, String email, Character type, boolean newHire, boolean active) {
        this.empid = empid;
        this.address = address;
        this.fname = fname;
        this.lname = lname;
        this.phoneno = phoneno;
        this.email = email;
        this.type = type;
        this.newHire = newHire;
        this.active = active;
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

    public String getFirstname() {
        return fname;
    }

    public void setFirstname(String fname) {
        this.fname = fname;
    }

    public String getLastname() {
        return lname;
    }

    public void setLastname(String lname) {
        this.lname = lname;
    }

    public String getPhoneNo() {
        return phoneno;
    }

    public void setPhoneNo(String phoneno) {
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

    @XmlTransient
    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    public EmployeeConstraints getConstraints() {
        return constraints;
    }

    public void setConstraints(EmployeeConstraints constraints) {
        this.constraints = constraints;
    }

    @XmlTransient
    public List<Notification> getNotificationsList() {
        return notificationsList;
    }

    public void setNotificationsList(List<Notification> notificationsList) {
        this.notificationsList = notificationsList;
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
        String result = constraints.toString() + "\n" + getFirstname() + " " + getLastname() + " " + getEmail() + " " + getPhoneNo() + " " + getType();
        return result;
    }

}
