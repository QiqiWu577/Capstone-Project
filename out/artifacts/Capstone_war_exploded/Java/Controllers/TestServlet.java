package Controllers;

import Model.Employee;
import Persistance.DBOperation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "TestServlet", urlPatterns = "/TestServlet")
public class TestServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {

        PasswordManager pm = new PasswordManager();
    DBOperation dbops = new DBOperation();
        byte[] salt1;
        boolean test1;
        byte[] salt2;
        boolean test2;
        byte[] salt3;
        boolean test3;


       // PasswordManager pm2 = new PasswordManager();

        System.out.println("ADMIN:");
        salt1 = pm.getSalt();
        String string1 = DatatypeConverter.printBase64Binary(salt1);
        String string2 = pm.hash("admin", salt1);
        System.out.println("Admin salt: " + string1);
        System.out.println("Admin hash: " + string2);
        System.out.println("Verify: "+pm.checkPassword(string2, "admin", string1));
        test1 = pm.getHashSalt(6666, "admin");
        System.out.println("Database: " + test1 + "\n\n");

        System.out.println("MANAGER:");
        salt2 = pm.getSalt();
        String string3 = DatatypeConverter.printBase64Binary(salt2);
        String string4 = pm.hash("manager", salt2);
        System.out.println("Manager salt: " + string3);
        System.out.println("Manager hash: " + string4);
        System.out.println("Verify: "+pm.checkPassword(string4, "manager", string3));
        test2 = pm.getHashSalt(7777, "manager");
        System.out.println("Database: " + test2 + "\n\n");


        System.out.println("EMPLOYEE:");
        salt3 = pm.getSalt();
        String string5 = DatatypeConverter.printBase64Binary(salt3);
        String string6 = pm.hash("employee", salt3);
        System.out.println("Employee salt: " + string5);
        System.out.println("Employee hash: " + string6);
        System.out.println("Verify: "+pm.checkPassword(string6, "employee", string5));
        test3 = pm.getHashSalt(8888, "employee");
        System.out.println("Database: " + test3 + "\n\n");

//        System.out.println("MANAGER:");
//        salt2 = pm.getSalt();
//        String string2 = DatatypeConverter.printBase64Binary(salt2);
//        System.out.println("Manager salt: " + string2);
//        System.out.println("Manager hash: " + pm.hash("manager", salt2));
//        test2 = pm.getHashSalt(7777, "manager");
//        System.out.println(test2+"\n\n");
//
//        System.out.println("EMPLOYEE:");
//        salt3 = pm.getSalt();
//        String string3 = DatatypeConverter.printBase64Binary(salt3);
//        System.out.println("Employee salt: " + string3);
//        System.out.println("Employee hash: " + pm.hash("employee", salt3));
//        test3 = pm.getHashSalt(8888, "employee");
//        System.out.println(test3+"\n\n");




    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
