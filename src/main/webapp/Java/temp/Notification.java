/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import org.joda.time.LocalDateTime;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "notifications")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Notifications.findAll", query = "SELECT n FROM Notification n")
        , @NamedQuery(name = "Notifications.findByNotifid", query = "SELECT n FROM Notification n WHERE n.notifid = :notifid")
        , @NamedQuery(name = "Notifications.findByRecipient", query = "SELECT n FROM Notification n WHERE n.recipient = :recipient")
        , @NamedQuery(name = "Notifications.findByContent", query = "SELECT n FROM Notification n WHERE n.content = :content")
        , @NamedQuery(name = "Notifications.findByDate", query = "SELECT n FROM Notification n WHERE n.date = :date")
        , @NamedQuery(name = "Notifications.findByNotiftype", query = "SELECT n FROM Notification n WHERE n.notiftype = :notiftype")
        , @NamedQuery(name = "Notifications.findByStatus", query = "SELECT n FROM Notification n WHERE n.status = :status")})
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

    @Transient
    private LocalDateTime sendTime;

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

    public Notification(int recipient, Date date, String content, Character notiftype, Character status) {

        this.recipient = recipient;
        this.date = date;
        this.content = content;
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

}
