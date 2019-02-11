package Model;

import org.joda.time.LocalDateTime;

public class Notification {

    private int notificationId;
    private String content;
    //For knowing who it is sent/sent to in the message fields (IE FROM:ANTHONY TO:MATT)
    private String sender;
    private String recipient;
    private LocalDateTime sendTime;
    private char type;

    public Notification(int notificationId, String content, String sender, String recipient, LocalDateTime sendTime, char type) {
        this.notificationId = notificationId;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.sendTime = sendTime;
        this.type = type;
    }


    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }
}