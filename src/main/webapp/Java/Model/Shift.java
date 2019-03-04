/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import temp.Employee;
import temp.ShiftPK;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
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
        , @NamedQuery(name = "Shift.findByShiftIdDayId", query = "SELECT s FROM Shift s WHERE s.shiftPK.shiftId = :shiftId and s.shiftPK.dayId = :dayId")
        , @NamedQuery(name = "Shift.findByShiftId", query = "SELECT s FROM Shift s WHERE s.shiftPK.shiftId = :shiftId")
        , @NamedQuery(name = "Shift.findByDayId", query = "SELECT s FROM Shift s WHERE s.shiftPK.dayId = :dayId")
        , @NamedQuery(name = "Shift.findByStartTime", query = "SELECT s FROM Shift s WHERE s.startTime = :startTime")
        , @NamedQuery(name = "Shift.findByEndTime", query = "SELECT s FROM Shift s WHERE s.endTime = :endTime")
        , @NamedQuery(name = "Shift.findByShiftName", query = "SELECT s FROM Shift s WHERE s.shiftName = :shiftName")
        , @NamedQuery(name = "Shift.findByShiftType", query = "SELECT s FROM Shift s WHERE s.shiftType = :shiftType")
        , @NamedQuery(name = "Shift.findByActive", query = "SELECT s FROM Shift s WHERE s.active = :active")})
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ShiftPK shiftPK;

    @Basic(optional = false)
    @Column(name = "startTime")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalTime startTime;

    @Basic(optional = false)
    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalTime endTime;

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
    private List<temp.Employee> employeesList;

    @JoinColumn(name = "day_id", referencedColumnName = "day_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Day dayId;

    @Transient
    private int minimumNumberOfEmployees;

    @Transient
    private int maximumNumberOfEmployees;

    @Transient
    private ArrayList<temp.Employee> empList;

    public Shift() {
    }

    public Shift(ShiftPK shiftPK, LocalTime startTime, LocalTime endTime, String shiftName, Character shiftType, boolean active) {

        this.shiftPK = shiftPK;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftName = shiftName;
        this.shiftType = shiftType;
        this.active = active;
    }

    public ShiftPK getShiftPK() {
        return shiftPK;
    }

    public void setShiftPK(ShiftPK shiftPK) {
        this.shiftPK = shiftPK;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
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
    public List<temp.Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<temp.Employee> employeesList) {
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

    public ArrayList<temp.Employee> getEmpList() {
        return empList;
    }

    public void setEmpList(ArrayList<Employee> empList) {
        this.empList = empList;
    }


}
