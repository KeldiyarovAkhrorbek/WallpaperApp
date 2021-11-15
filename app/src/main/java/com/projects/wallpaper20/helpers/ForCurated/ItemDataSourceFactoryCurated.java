package com.projects.wallpaper20.helpers.ForCurated;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.projects.wallpaper20.models.Photo;

public class ItemDataSourceFactoryCurated extends DataSource.Factory {


    public ItemDataSourceFactoryCurated() {

    }

    private MutableLiveData<PageKeyedDataSource<Integer, Photo>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        ItemDataSourceCurated itemDataSource = new ItemDataSourceCurated();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Photo>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
