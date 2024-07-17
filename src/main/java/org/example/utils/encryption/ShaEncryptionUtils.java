package org.example.utils.encryption;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaEncryptionUtils {
    public static void main(String[] args) {
        System.err.println(new String(sha256Encrypt("t2008e" , StandardCharsets.UTF_8)
                , StandardCharsets.UTF_8));
        System.err.println(new String(sha256Encrypt("t2008e", StandardCharsets.UTF_16)
                , StandardCharsets.UTF_8));
        System.err.println(new String(sha256Encrypt("t2008e ",StandardCharsets.UTF_8)
                , StandardCharsets.UTF_8));
    }
    public static byte[] sha256Encrypt(String input, Charset charsets){
        byte[] hashBytes;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
             hashBytes =  digest.digest(input.getBytes(charsets));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hashBytes;
    }

}
