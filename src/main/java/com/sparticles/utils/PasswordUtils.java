package com.sparticles.utils;

/**
 * @Description
 * @Date 2019/8/21 11:22 PM
 * @Auther smart
 **/
public class PasswordUtils {

    public static String generatePassword() {
        String[] pa = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < 4; i++) {
            sb.append(pa[(Double.valueOf(Math.random() * pa.length).intValue())]);
        }
        String[] spe = { "`","~","!","@","#","$","%","^","&","*","(",")","-","_","=","+","[","]","{","}","\\","/","?",",",".","<",">"};
        sb.append(spe[(Double.valueOf(Math.random() * spe.length).intValue())]);
        sb.append((int)(Math.random()*100));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generatePassword());
    }
}
