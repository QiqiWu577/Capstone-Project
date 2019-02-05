/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import Model.ScheduledEmployee;
import org.joda.time.LocalTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "shift")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s")
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
    private Date startTime;

    @Transient
    private LocalTime sTime;

    @Basic(optional = false)
    @Column(name = "endTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

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
    private Day days;

    @Transient
    private int minimumNumberOfEmployees;

    @Transient
    private int maximumNumberOfEmployees;

    @Transient
    private ArrayList<ScheduledEmployee> empList;

    public Shift() {
    }

    public Shift(ShiftPK shiftPK) {
        this.shiftPK = shiftPK;
    }

    public Shift(ShiftPK shiftPK, Date startTime, Date endTime, String shiftName, Character shiftType, boolean active) {
        this.shiftPK = shiftPK;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftName = shiftName;
        this.shiftType = shiftType;
        this.active = active;
    }

    public Shift(int shiftId, int dayId) {
        this.shiftPK = new ShiftPK(shiftId, dayId);
    }

    public ShiftPK getShiftPK() {
        return shiftPK;
    }

    public void setShiftPK(ShiftPK shiftPK) {
        this.shiftPK = shiftPK;
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

    public Day getDays() {
        return days;
    }

    public void setDays(Day days) {
        this.days = days;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shiftPK != null ? shiftPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.shiftPK == null && other.shiftPK != null) || (this.shiftPK != null && !this.shiftPK.equals(other.shiftPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Shift[ shiftPK=" + shiftPK + " ]";
    }

}
