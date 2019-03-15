package Controllers;


import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordManager {



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


}
