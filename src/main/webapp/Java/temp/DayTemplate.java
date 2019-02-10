/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import org.joda.time.LocalTime;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
@Table(name = "day_template")
@XmlRootElement
@NamedQueries({
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date openTime;

    @Column(name = "close_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeTime;

    @Transient
    private LocalTime oTime;
    @Transient
    private LocalTime cTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dayOfWeek")
    private List<ShiftTemplate> shiftTemplateList;


    public DayTemplate() {
    }

    //used to store the datatime into the database
    public DayTemplate(String dayOfWeek, LocalDateTime openTime, LocalDateTime closeTime) {

        this.dayOfWeek = dayOfWeek;
        this.openTime = Date.from(openTime.atZone(ZoneId.systemDefault()).toInstant());
        this.closeTime = Date.from(closeTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
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

}
