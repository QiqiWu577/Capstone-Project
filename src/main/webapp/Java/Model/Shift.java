/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import javax.persistence.*;
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
<<<<<<< HEAD
    @ManyToMany(mappedBy = "shiftList")
    private Set<Employee> employeeList;
=======
    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "shiftList")
    private List<Model.Employee> employeeList;
>>>>>>> 2f221d393b5e92e120022b64263752e819d9e948
    @JoinColumn(name = "day_id", referencedColumnName = "day_id")
    @ManyToOne
    private Day dayId;
    @Transient
    private int maxNoEmp;

    public int getMaxNoEmp() {
        return maxNoEmp;
    }

    public void setMaxNoEmp(int maxNoEmp) {
        this.maxNoEmp = maxNoEmp;
    }

    public int getMinNoEmp() {
        return minNoEmp;
    }

    public void setMinNoEmp(int minNoEmp) {
        this.minNoEmp = minNoEmp;
    }

    @Transient
    private int minNoEmp;

    public Shift() {
    }

    public Shift(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Shift(Integer shiftId, Date startTime, Date endTime, Character shiftType) {
        this.shiftId = shiftId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
    }

    public Shift(Day dayId, LocalDateTime startTime, LocalDateTime endTime, Character shiftType) {
        this.dayId = dayId;
        this.startTime = Date.from(startTime.atZone(ZoneId.systemDefault()).toInstant());
        this.endTime = Date.from(endTime.atZone(ZoneId.systemDefault()).toInstant());
        this.shiftType = shiftType;
    }

    public Shift(Integer shiftId, Date startTime, Date endTime, Character shiftType, int minNoEmp, int maxNoEmp) {
        this.shiftId = shiftId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shiftType = shiftType;
        this.minNoEmp = minNoEmp;
        this.maxNoEmp = maxNoEmp;
        //employeeList = new List<Employee>();
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public LocalDateTime getStartTime() {
        return startTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Character getShiftType() {
        return shiftType;
    }

    public void setShiftType(Character shiftType) {
        this.shiftType = shiftType;
    }


    public Date getStartTimeDate() {
        return startTime;
    }
    public Date getEndTimeDate() {
        return endTime;
    }

    @XmlTransient
    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = new HashSet<>(employeeList);
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
