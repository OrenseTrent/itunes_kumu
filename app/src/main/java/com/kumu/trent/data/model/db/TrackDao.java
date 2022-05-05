package com.kumu.trent.data.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kumu.trent.data.model.api.TrackModel;

import java.util.List;


@Dao
public interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<TrackModel> cats);

    @Query("SELECT DISTINCT * FROM track")
    LiveData<List<TrackModel>>  getTracks();

    @Query("DELETE FROM track")
    void deleteAll();

}
