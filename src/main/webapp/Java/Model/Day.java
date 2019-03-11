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
 * @author Administrator
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
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dayId")
    private List<Shift> shiftList;

    public Day() {
        shiftList = new ArrayList<Shift>();
    }

    public Day(Integer dayId) {
        this.dayId = dayId;
    }

    public Day(LocalDateTime startTime, LocalDateTime endTime) {

        this.startTime = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
        this.endTime = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public LocalDateTime getStartTime() {
        return startTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public LocalDateTime getEndTime() {
        return endTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @XmlTransient
    public Collection<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(ArrayList<Shift> shiftList) {
        this.shiftList = shiftList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dayId != null ? dayId.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {

        String shift = "---- Shifts ----\n";
        for(Shift s: shiftList) {
            shift += s.getStartTime().toString() + " " + s.getEndTime().toString() + "\n";
        }
        return "---Day---\n" + dayId + " " + startTime.toString() + " " + endTime.toString() + shift;
    }

}
