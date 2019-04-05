package Controllers;


import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Random;

public class PasswordManager {

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
        String SQL = "call set_password(?,?,?)";

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

    public static byte[] getSalt() throws NoSuchAlgorithmException
    {


        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);

        return salt;
    }


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

    public boolean checkPassword(String hash, String verify, String salt){

        byte[] byteSalt;

        byteSalt = DatatypeConverter.parseBase64Binary(salt);

        String generatedHash = hash(verify, byteSalt);
        return hash.equals(generatedHash);
    }

    public String generatePassword(){

        final String PASS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String tempPass=null;
        Random rand = new Random();


        for (int i =0; i<=8; i++) {
            int n = rand.nextInt(26);

            tempPass+=PASS.charAt(n);


        }

        return tempPass;
    }

}
