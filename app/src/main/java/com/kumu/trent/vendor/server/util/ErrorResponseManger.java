package com.kumu.trent.vendor.server.util;

import android.widget.TextView;

import java.util.List;



public class ErrorResponseManger {

    public static String first(List<?> list){
        if(list == null){
            return "";
        }
        if(list.size() == 0){
            return "";
        }
        return String.valueOf(list.get(0));
    }

    public static void first(TextView textView, List<?> list){
        if(list == null){
            return;
        }
        if(list.size() == 0){
            return;
        }
        textView.setError(String.valueOf(list.get(0)));
    }

    public static  String valueFromPosition(List<?> list, int position){
        if(list == null){
            return "";
        }

        if(list.size()-1 > position){
            return "";
        }

        return String.valueOf(list.get(position));
    }
}
