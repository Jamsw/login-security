package com.sparticles.utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * @Description
 * @Date 2019/8/21 11:05 PM
 * @Auther smart
 **/
public class PBKDF2Utils {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";

    public static String createPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] hash = pbkdf2(password.toCharArray(), salt.getBytes(), 5000, 128);
        return toHex(hash);
    }

    public static boolean validatePassword(String pwdToCheck, String salt, String correctPwd) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] testHash = pbkdf2(pwdToCheck.toCharArray(), salt.getBytes(), 5000, 128);
        return slowEquals(fromHex(correctPwd), testHash);
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;

        for(int i = 0; i < a.length && i < b.length; ++i) {
            diff |= a[i] ^ b[i];
        }

        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];

        for(int i = 0; i < binary.length; ++i) {
            binary[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = array.length * 2 - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }


    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String password = "000000";
        String salt = "18777991130";
        String hash = createPassword(password, salt);
        System.out.println(hash);
    }
}
