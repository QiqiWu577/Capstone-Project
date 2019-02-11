/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.time.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    private Date startTime;

    @Basic(optional = false)
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Transient
    private LocalDateTime openTime;
    @Transient
    private LocalDateTime closeTime;
    @Transient
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "days")
    private List<Shift> shiftList;

    public Day() {
    }

    //used to store the datatime into the database
    public Day(LocalDateTime openTime, LocalDateTime closeTime) {

        this.startTime = Date.from(openTime.atZone(ZoneId.systemDefault()).toInstant());
        this.endTime = Date.from(closeTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Integer getDayId() {
        return dayId;
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

    @XmlTransient
    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(ArrayList<Shift> shiftList) {
        this.shiftList = shiftList;
    }

}
