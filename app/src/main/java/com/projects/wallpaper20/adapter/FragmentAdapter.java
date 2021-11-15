package com.projects.wallpaper20.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.projects.wallpaper20.fragments.helper.ModelFragment;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStateAdapter {

    private ArrayList<String> categoryList;

    public FragmentAdapter(@NonNull Fragment fragment, ArrayList<String> categoryList) {
        super(fragment);
        this.categoryList = categoryList;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ModelFragment.newInstance(categoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
