package com.kumu.trent.vendor.android.java;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Formatter {
    public static String getDomainFromURL(String url){
        String data = "";
        try {
            URL u = new URL(url);
            return u.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String timePassed(String strTime, Date currentDate){

        try {

            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strTime);
            long time = date.getTime()  / 1000;
            long currentTime = currentDate.getTime() / 1000;

            long secondsAgo = currentTime - time;
            double period;

            if (secondsAgo < 60){
                if(secondsAgo < 30){
                    return "Just Now.";
                }else{
                    return secondsAgo == 1 ? "1 sec ago"     : secondsAgo + " secs ago";
                }
            } else if(secondsAgo < 3600){
                period = Math.floor(secondsAgo / 60);
                return  (int) period == 1 ? "1 min ago" : (int) period + " mins ago";
            }else if(secondsAgo < 86400){
                period = Math.floor(secondsAgo / 3600);
                return  (int) period == 1 ? "1 hr ago" : (int) period + " hrs ago";
            }else if(secondsAgo < 432000){
                period = Math.floor(secondsAgo / 86400);
                return  (int) period == 1 ? "1 day ago" : (int) period + " days ago";
            }else{

                if(date.getYear() == currentDate.getYear()){
                    return new SimpleDateFormat("MMMM dd").format(date);
                }else{
                    return new SimpleDateFormat("MMMM dd yyyy").format(date);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "--";
        }
    }



    public static String dateReformat(String dateString, String originalFormat, String newFormat){
        if(dateString == null){
            return "";
        }
        SimpleDateFormat originalFormatSDF = new SimpleDateFormat(originalFormat);
        SimpleDateFormat newFormatSDF = new SimpleDateFormat(newFormat);
        try {
            Date date = originalFormatSDF.parse(dateString);
            return newFormatSDF.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
