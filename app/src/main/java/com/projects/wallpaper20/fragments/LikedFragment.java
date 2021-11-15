package com.projects.wallpaper20.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.projects.wallpaper20.R;
import com.projects.wallpaper20.adapter.PhotoAdapterLiked;
import com.projects.wallpaper20.database.AppDatabase;
import com.projects.wallpaper20.databinding.FragmentLikedBinding;
import com.projects.wallpaper20.entity.PhotoEntity;

import java.util.List;

public class LikedFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LikedFragment() {
    }

    public static LikedFragment newInstance(String param1, String param2) {
        LikedFragment fragment = new LikedFragment();
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

    private FragmentLikedBinding binding;
    private AppDatabase appDatabase;
    private PhotoAdapterLiked adapterLiked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLikedBinding.inflate(inflater, container, false);
        appDatabase = AppDatabase.getInstance(requireContext());
        adapterLiked = new PhotoAdapterLiked(photo -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("photo", photo);
            Navigation.findNavController(requireView()).navigate(R.id.imageFragment, bundle);
        });
        List<PhotoEntity> allPhotos = appDatabase.photoDao().getAllPhotos();
        binding.recycler.setAdapter(adapterLiked);
        adapterLiked.submitList(allPhotos);
        return binding.getRoot();
    }
}