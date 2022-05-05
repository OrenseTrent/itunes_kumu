package com.kumu.trent.data.model.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.kumu.trent.data.model.api.TrackModel;


@Database(entities = {TrackModel.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "track.db";
    public abstract TrackDao trackDao();
    public static volatile AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyn(INSTANCE);
        }
    };

    static  class  PopulateDbAsyn extends AsyncTask<Void,Void,Void>{
        private TrackDao trackDao;
        public PopulateDbAsyn(AppDatabase catDatabase)
        {
            trackDao=catDatabase.trackDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            trackDao.deleteAll();
            return null;
        }
    }
}
