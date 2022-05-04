package com.kumu.trent.data.preference;

import android.content.SharedPreferences;

import com.kumu.trent.data.model.api.UserModel;
import com.google.gson.Gson;



public class UserData extends Data{

    public static final String USER_ITEM = "user_item";

    public static void insert(UserModel userModel){
        SharedPreferences.Editor editor = Data.getSharedPreferences().edit();
        editor.putString(USER_ITEM, new Gson().toJson(userModel));
        editor.commit();
    }

    public static UserModel getUserModel(){
        UserModel userModel = new Gson().fromJson(Data.getSharedPreferences().getString(USER_ITEM, ""), UserModel.class);
        if(userModel == null){
            userModel = new UserModel();
        }
        return userModel;
    }

    public static boolean isLogin(){
        return getUserModel().id != 0;
    }

    public static boolean isMe(int id){
        return getUserModel().id == id;
    }

    public static int getUserId(){
        return getUserModel().id;
    }
}
