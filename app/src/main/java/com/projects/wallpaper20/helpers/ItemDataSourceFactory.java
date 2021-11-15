package com.projects.wallpaper20.helpers;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.projects.wallpaper20.models.Photo;

public class ItemDataSourceFactory extends DataSource.Factory {

    private String query;

    public ItemDataSourceFactory(String query) {
        this.query = query;
    }

    private MutableLiveData<PageKeyedDataSource<Integer, Photo>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource(query);
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Photo>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
