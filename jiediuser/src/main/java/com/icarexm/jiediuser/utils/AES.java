package com.icarexm.jiediuser.utils;

import android.util.Base64;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static String KEY = "Guz(%&hj7x89H$yuB76*h%(HiKGddfssxfvb0456FtmaT5&fvHUFCy76*h%(HiKGddfssxfvbhjh95GUY86GfghUb#er57HBh(u%g6HJ($jhWklJ$lhj!y6&(*jkP87jH7";

    public AES() {
    }

    public static String encrypTo(String source) {
        byte[] bytes = null;

        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", new CryptoProvider());
            String asciiKey = toAscii();
            sr.setSeed(asciiKey.getBytes());
            keygen.init(128, sr);
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(1, new SecretKeySpec(keygen.generateKey().getEncoded(), "AES"));
            bytes = cipher.doFinal(source.getBytes("utf-8"));
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return Base64.encodeToString(bytes, 0);
    }

    public static String decrypTo(String source) {
        byte[] bytes = null;
        String result = null;

        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", new CryptoProvider());
            String asciiKey = toAscii();
            sr.setSeed(asciiKey.getBytes());
            keygen.init(128, sr);
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(2, new SecretKeySpec(keygen.generateKey().getEncoded(), "AES"));
            byte[] bytesss = cipher.doFinal(Base64.decode(source.getBytes(), 0));
            result = new String(bytesss, "UTF-8");
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return result;
    }

    private static String toAscii() {
        String ascii = "";

        for(int i = 0; i < KEY.length(); ++i) {
            ascii = ascii + String.format("%03d", Integer.valueOf(KEY.charAt(i)));
        }

        return ascii;
    }
}
