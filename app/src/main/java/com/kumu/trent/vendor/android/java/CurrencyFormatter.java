package com.kumu.trent.vendor.android.java;

import android.text.TextUtils;

/**
 * Created by BCTI 3 on 10/25/2017.
 */

public class CurrencyFormatter {

    public static String format(double num){
        return String.format("%,.2f", num);
    }

    public static String format(String num){
        if(TextUtils.isEmpty(num)){
            num = "0.00";
        }
        Double numParsed = Double.parseDouble(num);
        return String.format("%,.2f", numParsed);
    }
}
