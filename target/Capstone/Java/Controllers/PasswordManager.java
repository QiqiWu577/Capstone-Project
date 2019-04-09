package Controllers;


import Model.Employee;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Random;

/**
 * @author Matthew Kelemen
 */
public class PasswordManager {
    /**
     * gets the connection for the JDBC connection
     * @return JDBC connection object
     */
    private Connection getConnection() {

        Connection conn=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://2030bubbletea.mysql.database.azure.com:3306/schedulecapstone?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true&amp;useSSL=false", "adminDB@2030bubbletea", "Shuling5534848");
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return conn;
    }

    /**
     * Adds a user to the salt table
     * @param empid
     * @param password
     */
    public void addUser(int empid, String password) {

        PasswordManager pm = new PasswordManager();
        byte[] tempByte = null;

        try {
            tempByte = pm.getSalt();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String salt = DatatypeConverter.printBase64Binary(tempByte);
        String hash = pm.hash(password, tempByte);



        CallableStatement cstmt = null;

        Connection conn=getConnection();
        String SQL = "call set_passwords(?,?,?)";

        try {
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, empid);
            cstmt.setString(2, hash);
            cstmt.setString(3, salt);


            cstmt.executeUpdate();

            cstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * Checks if a user has given the correct username and password
     * @param empid employee id
     * @param verify Password to be checked
     * @return true if they've given the correct information
     */
    public boolean getHashSalt(int empid, String verify) {
        boolean valid=false;
        String hash = null;
        String salt = null;
        CallableStatement cstmt = null;

        Connection conn=getConnection();
        String SQL = "call get_login(?)";

        try {
            cstmt = conn.prepareCall(SQL);

            cstmt.setInt(1, empid);
            ResultSet rs = cstmt.executeQuery();

            rs.next();
            hash = rs.getString(1);
            salt = rs.getString(2);



            rs.close();
            cstmt.close();
            conn.close();

            //System.out.println(hash + " " + salt);
            valid = checkPassword(hash, verify, salt);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valid;
    }

    /**
     * Generates a salt to add to a password
     * @return a byte[] of values
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSalt() throws NoSuchAlgorithmException
    {


        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);

        return salt;
    }

    /**
     * Hashes a password with a salt
     * @param toHash password to hash
     * @param salt salt to add to password
     * @return hashed password and salt
     */
    public String hash(String toHash, byte[] salt){


        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] bytes = md.digest(toHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }


        return generatedPassword;
    }

    /**
     * checks if the given password matched the password for the user in the database
     * @param hash hashed password from the database
     * @param verify password to check
     * @param salt salt with the password
     * @return true if hashed passwords matches
     */
    public boolean checkPassword(String hash, String verify, String salt){

        byte[] byteSalt;

        byteSalt = DatatypeConverter.parseBase64Binary(salt);

        String generatedHash = hash(verify, byteSalt);
        return hash.equals(generatedHash);
    }

    /**
     * Generates an 8 character password
     * @return generated password
     */
    public String generatePassword(){

        final String PASS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String tempPass="";
        Random rand = new Random();

       // rand.nextInt();

        for (int i =0; i<=8; i++) {
            int n = rand.nextInt(35);

            tempPass+=PASS.charAt(n);


        }

        return tempPass;
    }
<<<<<<< HEAD
  }
=======





    public void updateScheduleEmployee(int s, Employee e) {
        Connection conn=getConnection();
        String sql = "INSERT INTO schedule_employee VALUES(?,?)";

        System.out.println("test1");
        System.out.println(s + " : "+ e.getEmpid());

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, s);
            ps.setInt(2, e.getEmpid());
            System.out.println(s + " : "+ e.getEmpid());
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {

            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


//    public void resetPassword(String username, ) {
//
//
//
//
//
//
//
//    }
}
>>>>>>> defdf40c81dd2aad1bea9069736d485ea2f5bae4
