package com.projects.wallpaper20.helpers;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.projects.wallpaper20.models.Photo;

public class ItemViewModel extends ViewModel {

    private String query;
    public LiveData itemPagedList;
    MutableLiveData<PageKeyedDataSource<Integer, Photo>> liveDataSource;

    public ItemViewModel(String query) {
        this.query = query;
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(query);
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }
}
