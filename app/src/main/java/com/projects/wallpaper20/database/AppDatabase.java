package com.projects.wallpaper20.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.projects.wallpaper20.dao.PhotoDao;
import com.projects.wallpaper20.entity.PhotoEntity;

import kotlin.jvm.Synchronized;


@Database(
        entities = {PhotoEntity.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PhotoDao photoDao();

    @Synchronized
    public static AppDatabase getInstance(Context context) {
        AppDatabase appDatabase = null;
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "my_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }

}
