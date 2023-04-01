package org.example.service.auth;

import lombok.Getter;
import lombok.Setter;
import org.example.db.MySqlDaoFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;

@Setter
@Getter
abstract public class Authentication extends MySqlDaoFactory {

    protected Connection dbConnection = null;
    protected Statement statement = null;

    private String login;
    private String password;
    private String email;

    protected String md5Custom(String st) {

        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }
}
