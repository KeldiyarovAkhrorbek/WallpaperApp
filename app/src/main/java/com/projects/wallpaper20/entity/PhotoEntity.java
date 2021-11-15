package com.projects.wallpaper20.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class PhotoEntity implements Serializable {
    private Long Height;
    private Long Width;
    private String PhotographerUrl;
    @NonNull
    @PrimaryKey
    private String Landscape;
    private String Photographer;
    private Boolean liked;

    public PhotoEntity() {
    }

    public PhotoEntity(Long height, Long width, String photographerUrl, String landscape, String photographer, Boolean liked) {
        Height = height;
        Width = width;
        PhotographerUrl = photographerUrl;
        Landscape = landscape;
        Photographer = photographer;
        this.liked = liked;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public PhotoEntity(Long height, Long width, String photographerUrl, String landscape, String photographer) {
        Height = height;
        Width = width;
        PhotographerUrl = photographerUrl;
        Landscape = landscape;
        Photographer = photographer;
    }

    public Long getHeight() {
        return Height;
    }

    public void setHeight(Long height) {
        Height = height;
    }

    public Long getWidth() {
        return Width;
    }

    public void setWidth(Long width) {
        Width = width;
    }

    public String getPhotographerUrl() {
        return PhotographerUrl;
    }

    public void setPhotographerUrl(String photographerUrl) {
        PhotographerUrl = photographerUrl;
    }

    public String getLandscape() {
        return Landscape;
    }

    public void setLandscape(String landscape) {
        Landscape = landscape;
    }

    public String getPhotographer() {
        return Photographer;
    }

    public void setPhotographer(String photographer) {
        Photographer = photographer;
    }
}
