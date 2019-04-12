/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Persistance.DBOperation;

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

/**Notification.java
 * Class describing all attributes, operations, named querys, and hibernate mapping for the Notification object
 *
 * @author Jason Sy
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
    @Column(name = "shift_id")
    private Integer shift_id;
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
    @JoinColumn(name = "Sender", referencedColumnName = "emp_id")
    @ManyToOne(optional = false)
    private Employee sender;

    /**
     * Default constructor for a Notification Object
     */
    public Notification() {
    }

    /**
     * Constructor to assign a ID to notification object
     *
     * @param notifid ID to assign to object
     */
    public Notification(Integer notifid) {
        this.notifid = notifid;
    }

    /**
     * Constructor to assign specified attributes to a notification object
     *
     * @param notifid ID to be set for notification object
     * @param recipient ID of the recipient of the notification object
     * @param date Date the notification was made
     * @param notiftype Type of notification (A - all, P - personal, D - department, S - shift)
     * @param status Status of notification object sent (W - waiting, A - accepted, D - declined, N - normal)
     */
    public Notification(Integer notifid, int recipient,Date date, Character notiftype, Character status) {
        this.notifid = notifid;
        this.recipient = recipient;
        this.date = date;
        this.notiftype = notiftype;
        this.status = status;
    }

    /**
     * Constructor to assign specified attributes to a notification object
     *
     * @param sender Employee object of the sender of the notification object
     * @param recipient ID of recipient receiving the notification object
     * @param shift_id ID of shift being offered in notification object
     * @param content String message attached to the notification object
     * @param notiftype Type of notification which will be 'S' in this case for a shift offer
     * @param status Status of notification object sent (W - waiting, A - accepted, D - declined, N - normal)
     */
    public Notification(Employee sender, int recipient, int shift_id, String content, Character notiftype, Character status) {
        this.sender = sender;
        this.recipient = recipient;
        this.shift_id = shift_id;
        this.content = content;
        this.date = new Date();
        this.notiftype = notiftype;
        this.status = status;
    }

    /**
     * Constructor to assign specified attributes to a notification object
     *
     * @param sender Employee object of the sender of the notification object
     * @param recipient ID of recipient receiving the notification object
     * @param content String message attached to the notification object
     * @param notiftype Type of notification (A - all, P - personal, D - department, S - shift)
     * @param status Status of notification object sent (W - waiting, A - accepted, D - declined, N - normal)
     */
    public Notification(Employee sender, int recipient, String content, Character notiftype, Character status) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.date = new Date();
        this.notiftype = notiftype;
        this.status = status;
    }

    /**
     *
     * @return the shift ID of the notification object
     */
    public Integer getShift_id() {
        return shift_id;
    }

    /**
     * Sets the shift ID of the notification object
     *
     * @param shift_id Shift ID to be set
     */
    public void setShift_id(Integer shift_id) {
        this.shift_id = shift_id;
    }

    /**
     *
     * @return the notification ID of the notification object
     */
    public Integer getNotifid() {
        return notifid;
    }

    /**
     * Sets the ID for the notification object
     *
     * @param notifid the ID to be set
     */
    public void setNotifid(Integer notifid) {
        this.notifid = notifid;
    }

    /**
     *
     * @return the recipient's employee ID of the notification Object
     */
    public int getRecipient() {
        return recipient;
    }

    /**
     * Method to return the recipient's first name given their employee ID
     *
     * @param id recipient's employee ID
     * @return recipient's first name
     */
    public String getRecipientName(int id) {
        Employee e = new Employee();
        DBOperation db = new DBOperation();
        e = db.getEmployee(id);
        return e.getFname();
    }

    /**
     * Sets recipient's ID for the notification object
     *
     * @param recipient ID of the recipient to be set
     */
    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    /**
     *
     * @return the string message content of the notification object
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the message content of a notification
     *
     * @param content the string message to be set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return the date the notification was made
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date for the notification object
     *
     * @param date to be set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return the character representation of the notification type (A - all, P - personal, D - department, S - shift)
     */
    public Character getNotiftype() {
        return notiftype;
    }

    /**
     * Sets the type for the notification object (A - all, P - personal, D - department, S - shift)
     *
     * @param notiftype the character representation for type of notification (A - all, P - personal, D - department, S - shift)
     */
    public void setNotiftype(Character notiftype) {
        this.notiftype = notiftype;
    }

    /**
     *
     * @return the character representation of the status of the notification object (W - waiting, A - accepted, D - declined, N - normal)
     */
    public Character getStatus() {
        return status;
    }

    /**
     * Sets the character representation of the status of the notification object (W - waiting, A - accepted, D - declined, N - normal)
     *
     * @param status the character representing the status of the notification object (W - waiting, A - accepted, D - declined, N - normal)
     */
    public void setStatus(Character status) {
        this.status = status;
    }

    /**
     *
     * @return the sender Employee object of the notification object
     */
    public Employee getSender() {
        return sender;
    }

    /**
     *
     * @return the sender's first name of the notification object
     */
    public String getSenderName() {return sender.getFname(); }

    /**
     * Sets the employee object for the notification object
     *
     * @param sender employee object to be set
     */
    public void setSender(Employee sender) {
        this.sender = sender;
    }

    /**
     *
     * @return a hash code
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notifid != null ? notifid.hashCode() : 0);
        return hash;
    }

    /**
     * Checks equality of notification objects
     *
     * @param object notification object to be checked
     * @return a boolean, true if matched, false if not
     */
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
