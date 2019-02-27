/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

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
    , @NamedQuery(name = "Shift.findByShiftName", query = "SELECT s FROM Shift s WHERE s.shiftName = :shiftName")
    , @NamedQuery(name = "Shift.findByShiftType", query = "SELECT s FROM Shift s WHERE s.shiftType = :shiftType")
    , @NamedQuery(name = "Shift.findByActive", query = "SELECT s FROM Shift s WHERE s.active = :active")})
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
    @Column(name = "shift_name")
    private String shiftName;
    @Basic(optional = false)
    @Column(name = "shift_type")
    private Character shiftType;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "day_id", referencedColumnName = "day_id")
    @ManyToOne
    private Day dayId;
    @JoinColumn(name = "emp_id", referencedColumnName = "Emp_id")
    @ManyToOne
    private Employee empId;

    public Shift() {
    }

    public Shift(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Shift(Integer shiftId, Date startTime, Date endTime, String shiftName, Character shiftType, boolean active) {
        this.shiftId = shiftId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftName = shiftName;
        this.shiftType = shiftType;
        this.active = active;
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

    public Day getDayId() {
        return dayId;
    }

    public void setDayId(Day dayId) {
        this.dayId = dayId;
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
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
