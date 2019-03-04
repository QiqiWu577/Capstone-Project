/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.time.*;
/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "days")
@NamedQueries({
        @NamedQuery(name = "Days.findAll", query = "SELECT d FROM Day d")
        , @NamedQuery(name = "Days.findByDayId", query = "SELECT d FROM Day d WHERE d.dayId = :dayId")
        , @NamedQuery(name = "Days.findByStartTime", query = "SELECT d FROM Day d WHERE d.startTime = :startTime")
        , @NamedQuery(name = "Days.findByEndTime", query = "SELECT d FROM Day d WHERE d.endTime = :endTime")})
public class Day implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id")
    private int dayId;

    @Basic(optional = false)
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startTime;

    @Basic(optional = false)
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "days")
    private List<Shift> shiftList;

    public Day() {
    }

    //used to store the datatime into the database
    public Day(LocalDateTime openTime, LocalDateTime closeTime) {

        this.startTime = openTime;
        this.endTime = closeTime;
    }

    public Integer getDayId() {
        return dayId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @XmlTransient
    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(ArrayList<Shift> shiftList) {
        this.shiftList = shiftList;
    }

}
