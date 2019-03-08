/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @OneToMany(mappedBy = "dayId")
    private List<Shift> shiftList;

    public Day() {
    }

    public Day(Integer dayId) {
        this.dayId = dayId;
    }

    public Day(Integer dayId, Date startTime, Date endTime) {
        this.dayId = dayId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public String getStartTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(startTime);
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getEndTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(endTime);
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @XmlTransient
    public List<Shift> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<Shift> shiftList) {
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
        return "data.Day[ dayId=" + dayId + " ]";
    }

}
