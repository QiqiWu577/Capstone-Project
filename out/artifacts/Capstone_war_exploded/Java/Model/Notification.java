package Model;

import org.joda.time.LocalDateTime;

public class Notification {

    private int notificationId;
    private String content;
    //For knowing who it is sent/sent to in the message fields (IE FROM:ANTHONY TO:MATT)
    private int sender;
    private int recipient;
    private LocalDateTime sendTime;
    private char type;

    public Notification(int notificationId, String content, int sender, int recipient, LocalDateTime sendTime, char type) {
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

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
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