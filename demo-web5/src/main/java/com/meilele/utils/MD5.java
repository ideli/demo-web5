package com.meilele.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String ecodeByMD5(String str) {
        String result = null;
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f' };
        if (str != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] source = str.getBytes("utf-8");
                md.update(source);
                byte[] tmp = md.digest();
                char[] chrs = new char[32];
                for (int i = 0, j = 0; i < 16; i++) {
                    byte b = tmp[i];
                    chrs[j++] = hexDigits[b >>> 4 & 0xf];
                    chrs[j++] = hexDigits[b & 0xf];
                }
                result = new String(chrs);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(MD5.ecodeByMD5("123"));
        System.out.println("/mantis/versions/1".matches("/mantis/versions/*"));
    }
}
