package com.projects.wallpaper20.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;

import com.projects.wallpaper20.R;
import com.projects.wallpaper20.adapter.PhotoAdapter;
import com.projects.wallpaper20.databinding.FragmentRandomBinding;
import com.projects.wallpaper20.entity.PhotoEntity;
import com.projects.wallpaper20.helpers.ForCurated.ItemViewModelCurated;
import com.projects.wallpaper20.models.Photo;

import java.util.Collections;

public class RandomFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public RandomFragment() {
    }

    public static RandomFragment newInstance(String param1, String param2) {
        RandomFragment fragment = new RandomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentRandomBinding binding;
    private PhotoAdapter photoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRandomBinding.inflate(inflater, container, false);
        photoAdapter = new PhotoAdapter(photo -> {
            PhotoEntity photoEntity = new PhotoEntity(photo.getHeight(), photo.getWidth(), photo.getPhotographerUrl(), photo.getSrc().getPortrait(), photo.getPhotographer(), false);
            Bundle bundle = new Bundle();
            bundle.putSerializable("photo", photoEntity);
            Navigation.findNavController(requireView()).navigate(R.id.imageFragment, bundle);
        });
        binding.recycler.setAdapter(photoAdapter);
        ItemViewModelCurated itemViewModelCurated = new ItemViewModelCurated();
        itemViewModelCurated.itemPagedList.observe(requireActivity(), new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Photo> items) {
                Collections.shuffle(items);
                photoAdapter.submitList(items);
            }
        });
        return binding.getRoot();
    }
}