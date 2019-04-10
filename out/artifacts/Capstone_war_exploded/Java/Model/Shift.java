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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *Shift.java - Class describing all attributes and operations for the Shift object
 *
 * @author Anthony Doucet and Qiqi Wu
 */
@Entity
@Table(name = "shift")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s")
        , @NamedQuery(name = "Shift.findByShiftId", query = "SELECT s FROM Shift s WHERE s.shiftId = :shiftId")
        , @NamedQuery(name = "Shift.findByStartTime", query = "SELECT s FROM Shift s WHERE s.startTime = :startTime")
        , @NamedQuery(name = "Shift.findByEndTime", query = "SELECT s FROM Shift s WHERE s.endTime = :endTime")
        , @NamedQuery(name = "Shift.findByShiftType", query = "SELECT s FROM Shift s WHERE s.shiftType = :shiftType")})
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
    @Basic(optional = false)
    @Column(name = "shift_type")
    private Character shiftType;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER,mappedBy = "shiftList")
    private List<Employee> employeeList;

    @JoinColumn(name = "day_id", referencedColumnName = "day_id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Day dayId;
    @Transient
    private int maxNoEmp;

    /**
     *
     * @return maxNoEmp - returns the maximum number of employees needed in a shift
     */
    public int getMaxNoEmp() {
        return maxNoEmp;
    }

    /**
     * Sets the maximum number of employees needed for a Shift object
     * @param maxNoEmp - The maximum number of employees to set
     */
    public void setMaxNoEmp(int maxNoEmp) {
        this.maxNoEmp = maxNoEmp;
    }

    /**
     *
     * @return minNoEmp - returns the minimum number of employees needed in a shift
     */
    public int getMinNoEmp() {
        return minNoEmp;
    }

    /**
     * Sets the minimum number of employees needed in a shift
     * @param minNoEmp - The minimum number of employees to set
     */
    public void setMinNoEmp(int minNoEmp) {
        this.minNoEmp = minNoEmp;
    }


    @Transient
    private int minNoEmp;

    /**
     * Default constructor for a shift object
     */
    public Shift() {
    }

    /**
     * Non-defult constructor for a shift object
     * @param shiftId - The shiftId to be set for a shift object
     */
    public Shift(Integer shiftId) {
        this.shiftId = shiftId;
    }

    /**
     * Non-default constructor for a shift object
     * @param shiftId - The shiftIf to be set for a shift object
     * @param startTime - The startTime to be set for a shift object
     * @param endTime - The endTime to be set for a shift object
     * @param shiftType - The shiftType to be set for a shift object
     */
    public Shift(Integer shiftId, Date startTime, Date endTime, Character shiftType) {
        this.shiftId = shiftId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
    }

    /**
     * Non-default constructor for a shift object
     * @param dayId - The dayId to be set for a shift object
     * @param startTime - The startTime to be set for a shift object
     * @param endTime - The endTime to be set for a shift object
     * @param shiftType - The shiftType to be set for a shift object
     */
    public Shift(Day dayId, LocalDateTime startTime, LocalDateTime endTime, Character shiftType) {
        this.dayId = dayId;
        this.startTime = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
        this.endTime = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
        this.shiftType = shiftType;
    }

    /**
     * Non-default constructor for a shift object
     * @param shiftId - The shiftId to be set for a shift object
     * @param startTime- The startTime to be set for a shift object
     * @param endTime - The endTime to be set for a shift object
     * @param shiftType - The shiftType to be set for a shift object
     * @param minNoEmp - The minNoEmp to be set for a shift object
     * @param maxNoEmp - The maxNoEmp to be set for a shift object
     */
    public Shift(Integer shiftId, Date startTime, Date endTime, Character shiftType, int minNoEmp, int maxNoEmp) {
        this.shiftId = shiftId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
        this.minNoEmp = minNoEmp;
        this.maxNoEmp = maxNoEmp;
        employeeList =  new ArrayList<>();
    }

    /**
     *
     * @return shiftId - Returns the shift if of a shift object
     */
    public Integer getShiftId() {
        return shiftId;
    }

    /**
     * Sets the shiftId for a shift object
     * @param shiftId - shiftId to be set
     */
    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    /**
     *
     * @return startTime - Returns the start time for a shift object
     */
    public LocalDateTime getStartTime() {
        return startTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * Sets the startTime for a shift object
     * @param startTime - startTime to be set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return endTime - returns the endTime for a shift object
     */
    public LocalDateTime getEndTime() {
        return endTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * Sets the endTime for a shift object
     * @param endTime - endTime to be set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * @return shiftType - returns the shiftType for a shift object
     */
    public Character getShiftType() {
        return shiftType;
    }

    /**
     * Sets the shiftType for a shift object
     * @param shiftType - shiftType to be set
     */
    public void setShiftType(Character shiftType) {
        this.shiftType = shiftType;
    }

    /**
     *
     * @return startTime - returns the startTime as a Date
     */
    public Date getStartTimeDate() {
        return startTime;
    }

    /**
     *
     * @return endTime - returns the endTime as a Date
     */
    public Date getEndTimeDate() {
        return endTime;
    }

    /**
     *
     * @return employeeList - returns a List of all employees
     */
    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * Sets employeeList for a shift object
     * @param employeeList - employeeList to be set
     */
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    /**
     *
     * @return dayId - returns the dayId for a shift object
     */
    public Day getDayId() {
        return dayId;
    }

    /**
     * Sets dayId for a shift object
     * @param dayId - dayId to be set
     */
    public void setDayId(Day dayId) {
        this.dayId = dayId;
    }

    /**
     *
     * @return hash - returns a hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shiftId != null ? shiftId.hashCode() : 0);
        return hash;
    }

    /**
     * Checks equality of shiftIds
     * @param object - Object to be checked for equality
     * @return boolean - returns a boolean depending on if the shiftId matches the shiftId from the object
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.shiftId == null && other.shiftId != null) || (this.shiftId != null && !this.shiftId.equals(other.shiftId))) {
            return false;
        }
        return true;
    }

    /**
     * Non-default to string for a ShiftTemplate object
     * @return String - returns a String with information pertaining to a Shift object.
     */
    @Override
    public String toString() {
        return "data.Shift[ shiftId=" + shiftId + " ]";
    }

}
