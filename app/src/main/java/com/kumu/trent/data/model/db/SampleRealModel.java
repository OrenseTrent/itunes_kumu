package com.kumu.trent.data.model.db;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class SampleRealModel extends RealmObject {

    /** Do not remove Default */
    @PrimaryKey
    public int db_id;
    private String created_at;
    private String updated_at;
    private String deleted_at;

    public static int getNextID(){
        Number num = Realm.getDefaultInstance().where(SampleRealModel.class).max("db_id");
        int nextID;
        if(num == null) {
            nextID = 1;
        } else {
            nextID = num.intValue() + 1;
        }

        return nextID;
    }

    public void nextID(){
        db_id = getNextID();
    }

    public void timestamp(){
        String timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        created_at = timestamp;
        updated_at = timestamp;
        deleted_at = "";
    }

    public void updatedAt(){
        String timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        updated_at = timestamp;
    }
    /** Do not remove Default */

    /** Code here */
    @SerializedName("id")
    public int id;

}