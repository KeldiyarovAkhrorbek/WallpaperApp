package com.projects.wallpaper20.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.projects.wallpaper20.adapter.FragmentAdapter;
import com.projects.wallpaper20.databinding.FragmentHomeBinding;
import com.projects.wallpaper20.databinding.ItemTabBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ArrayList<String> categoryList;
    private FragmentAdapter fragmentAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loadCategories();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        fragmentAdapter = new FragmentAdapter(this, categoryList);
        binding.viewpager.setAdapter(fragmentAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewpager, (tab, position) -> {
            ItemTabBinding itemTabBinding = ItemTabBinding.inflate(getLayoutInflater());
            tab.setCustomView(itemTabBinding.getRoot());
            itemTabBinding.text.setText(categoryList.get(position));
            if (position == 0) {
                itemTabBinding.circle.setVisibility(View.VISIBLE);
                itemTabBinding.text.setTextColor(Color.WHITE);
            } else {
                itemTabBinding.circle.setVisibility(View.INVISIBLE);
                itemTabBinding.text.setTextColor(Color.parseColor("#808a93"));
            }
        }).attach();
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ItemTabBinding itemTabBinding = ItemTabBinding.bind(tab.getCustomView());
                itemTabBinding.circle.setVisibility(View.VISIBLE);
                itemTabBinding.text.setTextColor(Color.WHITE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ItemTabBinding itemTabBinding = ItemTabBinding.bind(tab.getCustomView());
                itemTabBinding.circle.setVisibility(View.INVISIBLE);
                itemTabBinding.text.setTextColor(Color.parseColor("#808a93"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void loadCategories() {
        categoryList = new ArrayList<>();
        categoryList.add("Animals");
        categoryList.add("Technology");
        categoryList.add("Uzbekistan");
        categoryList.add("India");
        categoryList.add("Laptop");
        categoryList.add("Cars");
        categoryList.add("Guns");
        categoryList.add("Mountain");
        categoryList.add("Horses");
        categoryList.add("Movies");
        categoryList.add("Games");
        categoryList.add("Samsung");
        categoryList.add("Apple");
    }
}