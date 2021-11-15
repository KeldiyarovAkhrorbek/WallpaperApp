
package com.projects.wallpaper20.models;

import com.google.gson.annotations.SerializedName;

public class Src {

    @SerializedName("landscape")
    private String mLandscape;
    @SerializedName("large")
    private String mLarge;
    @SerializedName("large2x")
    private String mLarge2x;
    @SerializedName("medium")
    private String mMedium;
    @SerializedName("original")
    private String mOriginal;
    @SerializedName("portrait")
    private String mPortrait;
    @SerializedName("small")
    private String mSmall;
    @SerializedName("tiny")
    private String mTiny;

    public String getLandscape() {
        return mLandscape;
    }

    public void setLandscape(String landscape) {
        mLandscape = landscape;
    }

    public String getLarge() {
        return mLarge;
    }

    public void setLarge(String large) {
        mLarge = large;
    }

    public String getLarge2x() {
        return mLarge2x;
    }

    public void setLarge2x(String large2x) {
        mLarge2x = large2x;
    }

    public String getMedium() {
        return mMedium;
    }

    public void setMedium(String medium) {
        mMedium = medium;
    }

    public String getOriginal() {
        return mOriginal;
    }

    public void setOriginal(String original) {
        mOriginal = original;
    }

    public String getPortrait() {
        return mPortrait;
    }

    public void setPortrait(String portrait) {
        mPortrait = portrait;
    }

    public String getSmall() {
        return mSmall;
    }

    public void setSmall(String small) {
        mSmall = small;
    }

    public String getTiny() {
        return mTiny;
    }

    public void setTiny(String tiny) {
        mTiny = tiny;
    }

}
