package com.projects.wallpaper20.retrofit;

import com.projects.wallpaper20.models.PhotoData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {

    @Headers({"Authorization: 563492ad6f91700001000001c7459e386e14484ba5a050595a733b67"})
    @GET("search")
    Call<PhotoData> getSearchData(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int size
    );

    @Headers({"Authorization: 563492ad6f91700001000001c7459e386e14484ba5a050595a733b67"})
    @GET("curated")
    Call<PhotoData> getCurated(
            @Query("page") int page,
            @Query("per_page") int size
    );
}
