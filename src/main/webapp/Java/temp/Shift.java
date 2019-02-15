/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import org.joda.time.LocalTime;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "shift")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s")
        , @NamedQuery(name = "Shift.findByShiftId", query = "SELECT s FROM Shift s WHERE s.shiftId = :shiftId ")
        , @NamedQuery(name = "Shift.findByStartTime", query = "SELECT s FROM Shift s WHERE s.startTime = :startTime")
        , @NamedQuery(name = "Shift.findByEndTime", query = "SELECT s FROM Shift s WHERE s.endTime = :endTime")
        , @NamedQuery(name = "Shift.findByShiftName", query = "SELECT s FROM Shift s WHERE s.shiftName = :shiftName")
        , @NamedQuery(name = "Shift.findByShiftType", query = "SELECT s FROM Shift s WHERE s.shiftType = :shiftType")
        , @NamedQuery(name = "Shift.findByActive", query = "SELECT s FROM Shift s WHERE s.active = :active")})
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id")
    private Integer shiftId;

    @Basic(optional = false)
    @Column(name = "startTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Basic(optional = false)
    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Transient
    private LocalTime sTime;
    @Transient
    private LocalTime eTime;

    @Basic(optional = false)
    @Column(name = "shift_name")
    private String shiftName;

    @Basic(optional = false)
    @Column(name = "shift_type")
    private Character shiftType;

    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;

    @ManyToMany(mappedBy = "shiftList")
    private List<Employee> employeesList;

    @JoinColumn(name = "day_id", referencedColumnName = "day_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Day dayId;

    @Transient
    private int minimumNumberOfEmployees;

    @Transient
    private int maximumNumberOfEmployees;

    @Transient
    private ArrayList<Employee> empList;

    public Shift() {
    }

    public Shift(Date startTime, Date endTime, String shiftName, Character shiftType, boolean active) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftName = shiftName;
        this.shiftType = shiftType;
        this.active = active;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Character getShiftType() {
        return shiftType;
    }

    public void setShiftType(Character shiftType) {
        this.shiftType = shiftType;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    public Day getDayId() {
        return dayId;
    }

    public void setDayId(Day days) {
        this.dayId = days;
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


}
