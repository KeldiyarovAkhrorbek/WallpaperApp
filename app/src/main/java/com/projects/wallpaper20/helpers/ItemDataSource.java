package com.projects.wallpaper20.helpers;

import androidx.paging.PageKeyedDataSource;

import com.projects.wallpaper20.models.Photo;
import com.projects.wallpaper20.models.PhotoData;
import com.projects.wallpaper20.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Photo> {

    public static final int PAGE_SIZE = 50;
    private static final int FIRST_PAGE = 1;
    private static final String SITE_NAME = "stackoverflow";
    private String query;

    public ItemDataSource(String query) {
        this.query = query;
    }

    @Override
    public void loadInitial(LoadInitialParams<Integer> params, final LoadInitialCallback<Integer, Photo> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getSearchData(query, FIRST_PAGE, PAGE_SIZE)
                .enqueue(new Callback<PhotoData>() {
                    @Override
                    public void onResponse(Call<PhotoData> call, Response<PhotoData> response) {

                        if (response.body() != null) {

                            callback.onResult(response.body().getPhotos(), null, FIRST_PAGE + 1);

                        }

                    }

                    @Override
                    public void onFailure(Call<PhotoData> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadBefore(final LoadParams<Integer> params, final LoadCallback<Integer, Photo> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getSearchData(query, params.key, PAGE_SIZE)
                .enqueue(new Callback<PhotoData>() {
                    @Override
                    public void onResponse(Call<PhotoData> call, Response<PhotoData> response) {


                        if (response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getPhotos(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<PhotoData> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadAfter(final LoadParams<Integer> params, final LoadCallback<Integer, Photo> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getSearchData(query, params.key, PAGE_SIZE)
                .enqueue(new Callback<PhotoData>() {
                    @Override
                    public void onResponse(Call<PhotoData> call, Response<PhotoData> response) {

                        if (response.body() != null) {
                            if (response.body().getNextPage() != null) {
                                Integer key = params.key + 1;
                                callback.onResult(response.body().getPhotos(), key);
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<PhotoData> call, Throwable t) {

                    }
                });


    }
}
