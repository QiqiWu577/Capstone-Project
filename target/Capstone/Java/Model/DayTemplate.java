/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "day_template")
@XmlRootElement
@NamedQueries(value = {
        @NamedQuery(name = "DayTemplate.findAll", query = "SELECT d FROM DayTemplate d")
        , @NamedQuery(name = "DayTemplate.findByDayOfWeek", query = "SELECT d FROM DayTemplate d WHERE d.dayOfWeek = :dayOfWeek")
        , @NamedQuery(name = "DayTemplate.findByOpenTime", query = "SELECT d FROM DayTemplate d WHERE d.openTime = :openTime")
        , @NamedQuery(name = "DayTemplate.findByCloseTime", query = "SELECT d FROM DayTemplate d WHERE d.closeTime = :closeTime")})
public class DayTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "day_of_week")
    private String dayOfWeek;
    @Column(name = "open_time")
    private String openTime;
    @Column(name = "close_time")
    private String closeTime;
<<<<<<< HEAD
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dayOfWeek")
=======
    @Column(name = "not_the_same_day")
    private boolean notTheSameDay;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "dayOfWeek")
>>>>>>> ec43090ee58b7d1ccb79865124208db37982c5ae
    private List<ShiftTemplate> shiftTemplateList;

    public DayTemplate() {
    }

<<<<<<< HEAD
    public DayTemplate(String dayOfWeek,String openTime,String closeTime){
        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;
=======
    public DayTemplate(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
>>>>>>> ec43090ee58b7d1ccb79865124208db37982c5ae
    }

    public DayTemplate(String day, String s, String e, boolean n) {
        this.dayOfWeek = day;
        this.closeTime = e;
        this.openTime = s;
        this.notTheSameDay = n;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    @XmlTransient
    public List<ShiftTemplate> getShiftTemplateList() {
        return shiftTemplateList;
    }

    public void setShiftTemplateList(List<ShiftTemplate> shiftTemplateList) {
        this.shiftTemplateList = shiftTemplateList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DayTemplate)) {
            return false;
        }
        DayTemplate other = (DayTemplate) object;
        if ((this.dayOfWeek == null && other.dayOfWeek != null) || (this.dayOfWeek != null && !this.dayOfWeek.equals(other.dayOfWeek))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.DayTemplate[ dayOfWeek=" + dayOfWeek + " ]";
    }

}
