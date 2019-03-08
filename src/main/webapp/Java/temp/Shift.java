/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.Serializable;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @ManyToMany(mappedBy = "shiftList")
    private List<Employee> employeeList;
    @JoinColumn(name = "day_id", referencedColumnName = "day_id")
    @ManyToOne
    private Day dayId;

    public Shift() {
    }

    public Shift(int dayId, LocalDateTime startTime, LocalDateTime endTime, Character shiftType) {
        this.dayId.setDayId(dayId);
        this.startTime = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
        this.endTime = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
        this.shiftType = shiftType;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Character getShiftType() {
        return shiftType;
    }

    public void setShiftType(Character shiftType) {
        this.shiftType = shiftType;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Day getDayId() {
        return dayId;
    }

    public void setDayId(Day dayId) {
        this.dayId = dayId;
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
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.shiftId == null && other.shiftId != null) || (this.shiftId != null && !this.shiftId.equals(other.shiftId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Shift[ shiftId=" + shiftId + " ]";
    }

}
