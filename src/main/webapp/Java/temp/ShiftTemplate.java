/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import org.joda.time.LocalTime;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "shift_template")
@XmlRootElement
@NamedQueries({
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

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Transient
    private LocalTime sTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Transient
    private LocalTime eTime;

    @Column(name = "min_no_emp")
    private Integer minNoEmp;

    @Column(name = "max_no_emp")
    private Integer maxNoEmp;

    @JoinColumn(name = "day_of_week", referencedColumnName = "day_of_week")
    @ManyToOne
    private DayTemplate dayOfWeek;

    public ShiftTemplate() {
    }

    public ShiftTemplate(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
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

    public Integer getMinNoEmp() {
        return minNoEmp;
    }

    public void setMinNoEmp(Integer minNoEmp) {
        this.minNoEmp = minNoEmp;
    }

    public Integer getMaxNoEmp() {
        return maxNoEmp;
    }

    public void setMaxNoEmp(Integer maxNoEmp) {
        this.maxNoEmp = maxNoEmp;
    }

    public DayTemplate getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayTemplate dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shiftId != null ? shiftId.hashCode() : 0);
        return hash;
    }

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

    @Override
    public String toString() {
        return "data.ShiftTemplate[ shiftId=" + shiftId + " ]";
    }

}
