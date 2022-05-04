package com.kumu.trent.vendor.android.java.security;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;



public class SecurityParser {
    public SecurityParser(){

    }

    private String decrypt(String s) {
        byte[] data = new byte[0];
        try {
            data = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            String base64Encoded = Base64.encodeToString(data, Base64.DEFAULT);

            Log.e("Parser", "raw: \n" + s + "\n" + "->: \n" + base64Encoded + "\n******************");
            return base64Encoded;

        }
    }
}
