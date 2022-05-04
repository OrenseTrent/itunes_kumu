package com.kumu.trent.vendor.android.java;



public class StringFormatter {

    public static boolean isEmpty(String string) {
        if(string == null) return true;
        if(string.length() == 0) return true;
        if(string.equals("null")) return true;
        return false;
    }

    public static String first(String string) {
        if(string.length()>0){
            return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
        }
        return string;
    }

    public static String firstWord(String string) {
        if(string == null) return string;

        if(string.length()<0) return string;

        String [] words = string.split(" ");

        if(words.length < 0 ) return string;

        return words[0];
    }

    public static String titleCase(String string) {
        if(string == null) return string;

        if(string.length()<0) return string;

        StringBuilder sb = new StringBuilder(string.length());
        for(String word : string.split(" ")){
            sb.append(first(word));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

}
