package com.projects.wallpaper20.helpers.ForCurated;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.projects.wallpaper20.helpers.ItemDataSource;
import com.projects.wallpaper20.models.Photo;

public class ItemViewModelCurated extends ViewModel {

    public LiveData itemPagedList;
    MutableLiveData<PageKeyedDataSource<Integer, Photo>> liveDataSource;

    public ItemViewModelCurated() {
        ItemDataSourceFactoryCurated itemDataSourceFactory = new ItemDataSourceFactoryCurated();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE)
                        .build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();
    }
}
