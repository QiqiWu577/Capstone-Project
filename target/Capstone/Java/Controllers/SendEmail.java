package Controllers;


import Model.Employee;
import Persistance.DBOperation;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * SendEmail - Class describing all attributes and operations for a SendEmail object
 *
 * @author Matthew Kelemen
 */
public class SendEmail extends HttpServlet {


    final static String USERNAME = "2030bubbletea";
    final static String PASSWORD = "403bubbletea";

    DBOperation dbops;

    /**
     * Method that takes in a type to determine what email is sent to all employees. Sends an email to all employees via Gmail
     * @param type - The type of the email to be sent
     */
    public void sendFromGmailArray(String type) {

        DBOperation dbops = new DBOperation();

        String body="";
        String subject="";

        List<Employee> temp = dbops.getEmployees();

        //send email on new schedule
        //take in a type
        //schedule
        //new shift available
        String from = USERNAME;
        String pass = PASSWORD;
        List<String> to = new ArrayList<String>();


        for (int i =0; i<temp.size();i++) {
            System.out.println(i + " ---"+temp.get(i).getEmail());

            to.add(temp.get(i).getEmail());
            System.out.println(i+"+++"+to.get(i));

            //System.out.println(i+"+++"+to[i]);
        }


        if (type.equalsIgnoreCase("S")) {
            body = "PIZZA!";
            subject = "arraylist test";

        } else if(type.equalsIgnoreCase("N")) {

        }



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



        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.size()];

            // To get the array of addresses
            for( int i = 0; i < to.size(); i++ ) {
                toAddress[i] = new InternetAddress(to.get(i));
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

    /**
     * Method that sends an email to a signle employee
     * @param from - username of email account sending email
     * @param pass - password of email account sending email
     * @param to - the email of the recipient
     * @param subject - The subject of the email to be sent
     * @param body - The body of the email to be sent
     */
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

    /**
     * Method that determines the conent of the email being sent to a single employee
     * @param to - The email address of the recipient of the email
     * @param name - The name of the person that is being emailed. FName from employee
     * @param empID - The empId of the person that is being emailed. Used as employee username
     * @param type - The type of email that needs to be sent to the user
     */
    void sendEmailSingle(String to, String name, int empID, String type) {

        PasswordManager pm = new PasswordManager();

        String subject = "2030 Bubble Tea Password Notification";
        String body;




        if (type.equalsIgnoreCase("new")) {
            String tempPass = pm.generatePassword();

            pm.addUser(empID, tempPass);


            body = "Hello " + name + ",\r\n\r\nAn employee account has been made for you for 2030 Bubble Tea Cafe. " +
                    "Please login using the following information at http://localhost:8080/Capstone_war_exploded/" + ".\r\n\r\nUsername: "
                    + empID + "\r\nPassword: " + tempPass + "\r\n\r\nThe username is your employee ID and this will be used "
                    + "every time you login. You will be asked to change your password after the " + "" +
                    "first login.\r\n\r\nThank you,\r\n2030 Bubble Tea Cafe";


        } else if (type.equalsIgnoreCase("reset")) {

            String tempPass = pm.generatePassword();

            pm.addUser(empID, tempPass);

            body = "Hello " + name + ",\r\n\r\nA password reset request has been sent for your 2030 Bubble Tea Cafe login. " +
                    "Please login using the following information at http://localhost:8080/Capstone_war_exploded/" + ".\r\n\r\nUsername: "
                    + empID + "\r\nPassword: " + tempPass + "\r\n\r\nThe username is your employee ID and this will be used "
                    + "every time you login. You will be asked to change your password after the " + "" +
                    "first login.\r\n\r\nThank you,\r\n2030 Bubble Tea Cafe";

        } else {
            body="Please contact 2030 System Admin as something is wrong with the scheduling system.";
        }



        sendFromGmailSingle(USERNAME, PASSWORD, to, subject, body);


    }


}
