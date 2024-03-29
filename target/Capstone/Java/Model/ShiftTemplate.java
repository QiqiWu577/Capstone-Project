/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *ShiftTemplate.java - Class describing all attributes and operations for the ShiftTemplate object.
 *
 * @author Anthony Doucet
 *
 */
@Entity
@Table(name = "shift_template")
@XmlRootElement
@NamedQueries(value = {
        @NamedQuery(name = "ShiftTemplate.findAll", query = "SELECT s FROM ShiftTemplate s")
        , @NamedQuery(name = "ShiftTemplate.findByShiftId", query = "SELECT s FROM ShiftTemplate s WHERE s.shiftId = :shiftId")
        , @NamedQuery(name = "ShiftTemplate.findByStartTime", query = "SELECT s FROM ShiftTemplate s WHERE s.startTime = :startTime")
        , @NamedQuery(name = "ShiftTemplate.findByEndTime", query = "SELECT s FROM ShiftTemplate s WHERE s.endTime = :endTime")
        , @NamedQuery(name = "ShiftTemplate.findByMinNoEmp", query = "SELECT s FROM ShiftTemplate s WHERE s.minNoEmp = :minNoEmp")
        , @NamedQuery(name = "ShiftTemplate.findByMaxNoEmp", query = "SELECT s FROM ShiftTemplate s WHERE s.maxNoEmp = :maxNoEmp")})
public class ShiftTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id")
    private Integer shiftId;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private char type;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "min_no_emp")
    private Integer minNoEmp;
    @Column(name = "max_no_emp")
    private Integer maxNoEmp;
    @JoinColumn(name = "day_of_week", referencedColumnName = "day_of_week")
    @ManyToOne
    private DayTemplate dayOfWeek;

    /**
     * Default constructor for a ShiftTemplate object
     */
    public ShiftTemplate() {
    }

    /**
     * Non-default constructor for a ShiftTemplate object.
     *
     * @param shiftId - Shift id to be assigned to a ShiftTemplate object
     */
    public ShiftTemplate(Integer shiftId) {
        this.shiftId = shiftId;
    }

    /**
     *
     * @return shiftId - returns the shiftId of the ShiftTemplate object
     */
    public Integer getShiftId() {
        return shiftId;
    }

    /**
     *Sets the shiftId for a ShiftTemplate object
     *
     * @param shiftId - The shiftId to set
     */
    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    /**
     *
     * @return name - returns the name of the ShiftTemplate object
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for a ShiftTemplate object
     *
     * @param name - The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return type - returns the type or department of the shift
     */
    public char getType() {
        return type;
    }

    /**
     * Sets the type for a ShiftTemplate object. Examples are B for bartender, K for cook, S for server
     * @param type - The type to set
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     *
     * @return startTime - Returns the time that the shift starts
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time for a ShiftTemplate object. Shows when the start of the shift is.
     * @param startTime - The start time to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return endTime - Returns the time that a shift ends
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time for a ShiftTemplate object. Shows when the end of the shift is
     * @param endTime - The end time to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * @return minNoEmp - Returns the minimum number of employees needed for a shift
     */
    public Integer getMinNoEmp() {
        return minNoEmp;
    }

    /**
     * Sets the minimum amount of employees needed for a shift
     * @param minNoEmp - Minimum number of employees to set for a ShiftTemplate object
     */
    public void setMinNoEmp(Integer minNoEmp) {
        this.minNoEmp = minNoEmp;
    }

    /**
     *
     * @return maxNoEmp - Returns the maximum number of employees needed for a shift.
     */
    public Integer getMaxNoEmp() {
        return maxNoEmp;
    }

    /**
     * Sets the maximum amount of employees needed for a shift
     * @param maxNoEmp - Maximum number of employees to be set for a ShiftTemplate object
     */
    public void setMaxNoEmp(Integer maxNoEmp) {
        this.maxNoEmp = maxNoEmp;
    }

    /**
     *
     * @return dayOfWeek - Returns the day of the week that the shift falls on
     */
    public String getDayOfWeek() {
        return dayOfWeek.getDayOfWeek();
    }

    /**
     *Sets the day of the week for a ShiftTemplate object.
     * @param dayOfWeek - The day of the week to be set
     */
    public void setDayOfWeek(DayTemplate dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     *
     * @return hash - returns hashcode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shiftId != null ? shiftId.hashCode() : 0);
        return hash;
    }

    /**
     * Checks equality of shiftids
     * @param object - Object to be checked for equality
     * @return boolean - returns a boolean depending on if the shiftId matches the shiftId from the object
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShiftTemplate)) {
            return false;
        }
        ShiftTemplate other = (ShiftTemplate) object;
        if ((this.shiftId == null && other.shiftId != null) || (this.shiftId != null && !this.shiftId.equals(other.shiftId))) {
            return false;
        }
        return true;
    }

    /**
     * Non-default to string for a ShiftTemplate object
     * @return String - returns a String with information pertaining to a ShiftTemplate object.
     */
    @Override
    public String toString() {
        return "data.ShiftTemplate[ shiftId=" + shiftId + " ]";
    }

}
