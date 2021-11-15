
package com.projects.wallpaper20.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PhotoData {

    @SerializedName("next_page")
    private String mNextPage;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("per_page")
    private Long mPerPage;
    @SerializedName("photos")
    private List<Photo> mPhotos;
    @SerializedName("prev_page")
    private String mPrevPage;
    @SerializedName("total_results")
    private Long mTotalResults;

    public String getNextPage() {
        return mNextPage;
    }

    public void setNextPage(String nextPage) {
        mNextPage = nextPage;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public Long getPerPage() {
        return mPerPage;
    }

    public void setPerPage(Long perPage) {
        mPerPage = perPage;
    }

    public List<Photo> getPhotos() {
        return mPhotos;
    }

    public void setPhotos(List<Photo> photos) {
        mPhotos = photos;
    }

    public String getPrevPage() {
        return mPrevPage;
    }

    public void setPrevPage(String prevPage) {
        mPrevPage = prevPage;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
