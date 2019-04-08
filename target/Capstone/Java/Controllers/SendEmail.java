
package Controllers;


import Persistance.DBOperation;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

public class SendEmail extends HttpServlet {


    final static String USERNAME = "2030bubbletea";
    final static String PASSWORD = "403bubbletea";

    DBOperation dbops = new DBOperation();


    private static void sendFromGmailArray(String from, String pass, String[] to, String subject, String body) {

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        //

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException ae) {
            ae.printStackTrace();
        }

    }

    private static void sendFromGmailSingle(String from, String pass, String to, String subject, String body) {

        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        //

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress;

            // To get the array of addresses
                toAddress = new InternetAddress(to);


                message.addRecipient(Message.RecipientType.TO, toAddress);


            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException ae) {
            ae.printStackTrace();
        }

    }


    void sendEmailSingle(String to, String name, int empID, String type) {

     PasswordManager pm = new PasswordManager();

        String subject = "****TEST EMAIL****";
        String body;




        if (type.equalsIgnoreCase("new")) {
            String tempPass = pm.generatePassword();

<<<<<<< HEAD
           //pm.addUser(empID, tempPass);
=======
           pm.addUser(empID, tempPass);


>>>>>>> 5250c3eeb374f58dddb4f3be93c422b6945941af

            body =  "Hello "+ name +",\r\n\r\nAn employee account has been made for you for 2030 Bubble Tea Cafe. "+
                    "Please login using the following information at http://localhost:8080/Capstone_war_exploded/"+".\r\n\r\nUsername: "
                    +empID+"\r\nPassword: "+tempPass+"\r\n\r\nThe username is your employee ID and this will be used "
                    +"everytime you login. You will be asked to change your password after the "+"" +
                    "first login.\r\n\r\nThank you,\r\n2030 Bubble Tea Cafe";







        } else {
            body="woem";
        }



        System.out.println("BEFORE");
        sendFromGmailSingle(USERNAME, PASSWORD, to, subject, body);
        System.out.println("AFTER");


    }

    private void sendEmailArray(String[] to, String type) {


        String subject = "****TEST EMAIL****";
        String body;


        if (type.equalsIgnoreCase("new")) {


           body =  "Hello "+",\r\n\r\nAn employee account has been made for you for 2030 Bubble Tea Cafe. "+
                   "Please login using the following information at http://localhost:8080/Capstone_war_exploded/"+".\r\n\r\nUsername: "
                   +"\r\nPassword: "+"\r\n\r\nThe username is you employee ID and this will be used"
            +"everytime you login. You will be asked to change your password after the "+"" +
                    "first login.\r\n\r\nThank you,\r\n2030 Bubble Tea Cafe";







        } else {
            body="woem";
        }



        System.out.println("BEFORE");
        sendFromGmailArray(USERNAME, PASSWORD, to, subject, body);
        System.out.println("AFTER");


    }
}
