/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
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

    public ShiftTemplate() {
    }

    public ShiftTemplate(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public Integer getShiftId() {
        return shiftId;
    }

<<<<<<< HEAD
=======
    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

>>>>>>> ec43090ee58b7d1ccb79865124208db37982c5ae
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getDayOfWeek() {
        return dayOfWeek.getDayOfWeek();
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
