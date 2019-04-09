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
 * @author Anthony Doucet, Qiqi Wu
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
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "dayOfWeek")
    private List<ShiftTemplate> shiftTemplateList;

    /**
     * initializes a day template
     */
    public DayTemplate() {
    }

    /**
     * Initializes a day template with a day of the week, opening time, and closing time
     * @param dayOfWeek
     * @param openTime
     * @param closeTime
     */
    public DayTemplate(String dayOfWeek,String openTime,String closeTime) {
        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    /**
     * gets the day of the week for the day template ex: "Monday"
     * @return day of the week for the day template
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets the day of the week for the day template
     * @param dayOfWeek day of the week to set
     */
    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * gets the opening time for the day of the week as a string format HH:MM:SS
     * @return opening time for the day template
     */
    public String getOpenTime() {
        return openTime;
    }

    /**
     * Sets the opening time as string format: HH:MM:SS
     * @param openTime opening time of the day template
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    /**
     * gets the close time as a string in the format HH:MM:SS
     * @return closing time of the daytemplate
     */
    public String getCloseTime() {
        return closeTime;
    }

    /**
     * sets the close time as a string in the format HH:MM:SS
     * @param closeTime closing time of the day template
     */
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * gets the shift of shift templates for a
     * @return the list of shift templates for the day template
     */
    @XmlTransient
    public List<ShiftTemplate> getShiftTemplateList() {
        return shiftTemplateList;
    }

    public void setShiftTemplateList(List<ShiftTemplate> shiftTemplateList) {
        this.shiftTemplateList = shiftTemplateList;
    }

    /**
     * gets a hash of the objects
     * @return hash of the object
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
        return hash;
    }

    /**
     * compares day templates using day of the week
     * @param object daytemplate to compare
     * @return true if the objects are the same
     */
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

    /**
     * creates a string represntation of the object
     * @return a string representing the object
     */
    @Override
    public String toString() {
        return "data.DayTemplate[ dayOfWeek=" + dayOfWeek + " ]";
    }

}
