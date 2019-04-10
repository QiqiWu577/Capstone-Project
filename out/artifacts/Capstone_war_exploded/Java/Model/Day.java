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
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Anthony Doucet, Qiqi Qu
 */
@Entity
@Table(name = "days")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Day.findAll", query = "SELECT d FROM Day d")
    , @NamedQuery(name = "Day.findByDayId", query = "SELECT d FROM Day d WHERE d.dayId = :dayId")
    , @NamedQuery(name = "Day.findByStartTime", query = "SELECT d FROM Day d WHERE d.startTime = :startTime")
    , @NamedQuery(name = "Day.findByEndTime", query = "SELECT d FROM Day d WHERE d.endTime = :endTime")})
public class Day implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id")
    private Integer dayId;
    @Basic(optional = false)
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Basic(optional = false)
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @OneToMany(mappedBy = "dayId")
    private List<Shift> shiftList;

    /**
     * initializes a new day
     */
    public Day() {
        shiftList = new ArrayList<Shift>();
    }

    /**
     * Initializes a new day with an id
     * @param dayId id of the day
     */
    public Day(Integer dayId) {
        this.dayId = dayId;
    }

    /**
     * Initializes a new day
     * @param startTime
     * @param endTime
     */
    public Day(LocalDateTime startTime, LocalDateTime endTime) {

        this.startTime = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
        this.endTime = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * gets the day ID
     * @return the Id of the object
     */
    public Integer getDayId() {
        return dayId;
    }

    /**
     * sets the day id
     * @param dayId dayId to set
     */
    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    /**
     * gets the startTime of the day as a localdatetime
     * @return localdatetime representation of the start time
     */
    public LocalDateTime getStartTime() {
        return startTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * sets the startTime from a localdatetime
     * @param startTime starttime to set
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * gets the endTime of the day as a localdatetime
     * @return localdatetime representation of the start time
     */
    public LocalDateTime getEndTime() {
        return endTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * sets the endtime from a localdatetime
     * @param endTime endTime to start
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * gets a list of shifts on a given day
     * @return a list of shifts on the given day
     */
    @XmlTransient
    public Collection<Shift> getShiftList() {
        return shiftList;
    }

    /**
     * sets the shift list
     * @param shiftList shiftlist to set
     */
    public void setShiftList(ArrayList<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    /**
     * gets the hash code
     * @return hashcode of the object
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dayId != null ? dayId.hashCode() : 0);
        return hash;
    }

    /**
     * Compares 2 day objects based on ID
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Day)) {
            return false;
        }
        Day other = (Day) object;
        if ((this.dayId == null && other.dayId != null) || (this.dayId != null && !this.dayId.equals(other.dayId))) {
            return false;
        }
        return true;
    }

    /**
     * A String representation of the object
     * @return a string representation of an object
     */
    @Override
    public String toString() {

        String shift = "---- Shifts ----\n";
        for(Shift s: shiftList) {
            shift += s.getStartTime().toString() + " " + s.getEndTime().toString() + "\n";
        }
        return "---Day---\n" + dayId + " " + startTime.toString() + " " + endTime.toString() + shift;
    }

}
