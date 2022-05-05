package com.kumu.trent.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.kumu.trent.data.model.api.TrackModel;
import com.kumu.trent.data.model.db.AppDatabase;
import com.kumu.trent.data.model.db.TrackDao;

import java.util.List;

public class Repository {

    public TrackDao trackDao;
    public LiveData<List<TrackModel>> getAllTracks;
    private AppDatabase database;

    public Repository(Application application){
        database=AppDatabase.getInstance(application);
        trackDao=database.trackDao();
        getAllTracks=trackDao.getTracks();

    }

    public void insert(List<TrackModel> cats){
        new InsertAsyncTask(trackDao).execute(cats);

    }

    public LiveData<List<TrackModel>> getAllTracks(){
        return getAllTracks;
    }
    private static class InsertAsyncTask extends AsyncTask<List<TrackModel>,Void,Void>{
        private TrackDao trackDao;

        public InsertAsyncTask(TrackDao trackDao)
        {
            this.trackDao=trackDao;
        }
        @Override
        protected Void doInBackground(List<TrackModel>... lists) {
            trackDao.insert(lists[0]);
            return null;
        }
    }
}
