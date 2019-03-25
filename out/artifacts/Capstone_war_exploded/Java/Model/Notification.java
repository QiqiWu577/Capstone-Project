/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
@Table(name = "notifications")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
        , @NamedQuery(name = "Notification.findByNotifid", query = "SELECT n FROM Notification n WHERE n.notifid = :notifid")
        , @NamedQuery(name = "Notification.findByRecipient", query = "SELECT n FROM Notification n WHERE n.recipient = :recipient")
        , @NamedQuery(name = "Notification.findByContent", query = "SELECT n FROM Notification n WHERE n.content = :content")
        , @NamedQuery(name = "Notification.findByDate", query = "SELECT n FROM Notification n WHERE n.date = :date")
        , @NamedQuery(name = "Notification.findByNotiftype", query = "SELECT n FROM Notification n WHERE n.notiftype = :notiftype")
        , @NamedQuery(name = "Notification.findByStatus", query = "SELECT n FROM Notification n WHERE n.status = :status")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Notif_id")
    private Integer notifid;
    @Basic(optional = false)
    @Column(name = "Recipient")
    private int recipient;
    @Column(name = "Content")
    private String content;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "Notif_type")
    private Character notiftype;
    @Basic(optional = false)
    @Column(name = "Status")
    private Character status;
    @JoinColumn(name = "Sender", referencedColumnName = "Emp_id")
    @ManyToOne(optional = false)
    private Employee sender;

    public Notification() {
    }

    public Notification(Integer notifid) {
        this.notifid = notifid;
    }

    public Notification(Integer notifid, int recipient, Date date, Character notiftype, Character status) {
        this.notifid = notifid;
        this.recipient = recipient;
        this.date = date;
        this.notiftype = notiftype;
        this.status = status;
    }

    public Integer getNotifid() {
        return notifid;
    }

    public void setNotifid(Integer notifid) {
        this.notifid = notifid;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Character getNotiftype() {
        return notiftype;
    }

    public void setNotiftype(Character notiftype) {
        this.notiftype = notiftype;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Employee getSender() {
        return sender;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notifid != null ? notifid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notifid == null && other.notifid != null) || (this.notifid != null && !this.notifid.equals(other.notifid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Notification[ notifid=" + notifid + " ]";
    }

}
