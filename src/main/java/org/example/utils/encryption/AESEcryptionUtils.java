package org.example.utils.encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESEcryptionUtils {
    // get secret key  : get from random , get from secret string
    private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
    public static void main(String[] args) throws InvalidAlgorithmParameterException,
            NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String txt = "abc1234";
        SecretKey secretKey = generatorKey(AESKeySize.SIZE_256);
        IvParameterSpec parameterSpec = genSpec();
        System.err.println(encrypt(AES_ALGORITHM,txt, secretKey,parameterSpec));
    }
    public static String encrypt(String algorithm, String input
    ,SecretKey key , IvParameterSpec spec)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
            Cipher cipher = Cipher.getInstance(algorithm);
//            cipher.init(1, key,spec); fixme : hardCode value encryptMode
            cipher.init(Cipher.ENCRYPT_MODE, key ,spec);
            byte[] bytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
    }


    public static SecretKey  generatorKey(AESKeySize aesKeySize){
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(aesKeySize.getVal());
            return generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static IvParameterSpec genSpec(){
     byte[] iv  = new byte[16]; // hardcode
     new SecureRandom().nextBytes(iv);
     return new IvParameterSpec(iv);
    }
}
