package com.projects.wallpaper20.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.projects.wallpaper20.entity.PhotoEntity;

import java.util.List;


@Dao
public interface PhotoDao {

    @Insert(onConflict = REPLACE)
    void addPhoto(PhotoEntity photoEntity);

    @Delete
    void deletePhoto(PhotoEntity photoEntity);

    @Query("select * from PhotoEntity")
    List<PhotoEntity> getAllPhotos();

    @Query("SELECT EXISTS(SELECT * FROM PhotoEntity WHERE :landscapeUrl = Landscape)")
    Boolean isAvailable(String landscapeUrl);

    @Query("select * from PhotoEntity where Landscape = :url")
    PhotoEntity getPhotoByUrl(String url);
}
