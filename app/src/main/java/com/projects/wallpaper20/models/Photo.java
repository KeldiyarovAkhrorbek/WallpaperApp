
package com.projects.wallpaper20.models;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("avg_color")
    private String mAvgColor;
    @SerializedName("height")
    private Long mHeight;
    @SerializedName("id")
    private Long mId;
    @SerializedName("liked")
    private Boolean mLiked;
    @SerializedName("photographer")
    private String mPhotographer;
    @SerializedName("photographer_id")
    private Long mPhotographerId;
    @SerializedName("photographer_url")
    private String mPhotographerUrl;
    @SerializedName("src")
    private Src mSrc;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("width")
    private Long mWidth;

    public String getAvgColor() {
        return mAvgColor;
    }

    public void setAvgColor(String avgColor) {
        mAvgColor = avgColor;
    }

    public Long getHeight() {
        return mHeight;
    }

    public void setHeight(Long height) {
        mHeight = height;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Boolean getLiked() {
        return mLiked;
    }

    public void setLiked(Boolean liked) {
        mLiked = liked;
    }

    public String getPhotographer() {
        return mPhotographer;
    }

    public void setPhotographer(String photographer) {
        mPhotographer = photographer;
    }

    public Long getPhotographerId() {
        return mPhotographerId;
    }

    public void setPhotographerId(Long photographerId) {
        mPhotographerId = photographerId;
    }

    public String getPhotographerUrl() {
        return mPhotographerUrl;
    }

    public void setPhotographerUrl(String photographerUrl) {
        mPhotographerUrl = photographerUrl;
    }

    public Src getSrc() {
        return mSrc;
    }

    public void setSrc(Src src) {
        mSrc = src;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public Long getWidth() {
        return mWidth;
    }

    public void setWidth(Long width) {
        mWidth = width;
    }

}
