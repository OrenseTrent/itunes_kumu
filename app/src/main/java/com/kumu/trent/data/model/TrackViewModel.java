package com.kumu.trent.data.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kumu.trent.data.model.api.TrackModel;
import com.kumu.trent.data.repository.Repository;

import java.util.List;

public class TrackViewModel extends AndroidViewModel {
    private Repository repository;
    public LiveData<List<TrackModel>> getAllTracks;

    public TrackViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        getAllTracks=repository.getAllTracks();
    }

    public void insert(List<TrackModel> tracks){
        repository.insert(tracks);
    }

    public LiveData<List<TrackModel>> getAllTracks()
    {
        return getAllTracks;
    }
}
