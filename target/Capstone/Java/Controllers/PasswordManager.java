package Controllers;


import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Random;

/**
 * PasswordManager.java - Class describing all attributes and operations for a PasswordManager object
 *
 * @author Matthew Kelemen
 */
public class PasswordManager {

    /**
     * Opens a connection to the MySQL database
     * @return conn - A connection to the database
     */
    private Connection getConnection() {

        Connection conn=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/schedulecapstone", "root", "2030bubbletea");
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return conn;
    }

    /**
     * Adds a user to the password/salt table in the database.
     * @param empid - empId of the user to be added to the password table
     * @param password - password that is to be hashed and added to the database
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
     * This is the method that verifies if the password entered on the login screen matches the password that is in the password table.
     * @param empid
     * @param verify
     * @return
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
     * Generates a salt that will be used to hash a password before being stored to the database.
     * @return salt - random mix of characters that is used to hash the password
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
     * This method generates a hash based off of a password and salt.
     * @param toHash - password that is to be hashed and added to database
     * @param salt - salt that is to be added to password before password is hashed and stored
     * @return generatedPassword - the hashed password is returned
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
     * This is a helper method that helps determine if the password entered and the password in the database are the same.
     * @param hash - hash to be used to check passwords
     * @param verify - password that is entered to be comared to hash
     * @param salt - salt that is used to hash the password
     * @return boolean - returns a boolean. True if password entered matches password in database. False if password entered does not match password in database.
     */
    public boolean checkPassword(String hash, String verify, String salt){

        byte[] byteSalt;

        byteSalt = DatatypeConverter.parseBase64Binary(salt);

        String generatedHash = hash(verify, byteSalt);
        return hash.equals(generatedHash);
    }

    /**
     * This method generates a random password of 8 characters that will be used as the temporary password for new hires and reset passwords
     * @return tempPass - a temporary String of 8 random characters. AlphaNumeric
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


}
