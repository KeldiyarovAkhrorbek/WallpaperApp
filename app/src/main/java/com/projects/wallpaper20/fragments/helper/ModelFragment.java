package com.projects.wallpaper20.fragments.helper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;

import com.projects.wallpaper20.R;
import com.projects.wallpaper20.adapter.PhotoAdapter;
import com.projects.wallpaper20.databinding.FragmentModelBinding;
import com.projects.wallpaper20.entity.PhotoEntity;
import com.projects.wallpaper20.helpers.ItemViewModel;
import com.projects.wallpaper20.models.Photo;

public class ModelFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public ModelFragment() {
    }

    public static ModelFragment newInstance(String param1) {
        ModelFragment fragment = new ModelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    private FragmentModelBinding binding;
    private PhotoAdapter photoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentModelBinding.inflate(inflater, container, false);
        String query = mParam1;
        ItemViewModel itemViewModel = new ItemViewModel(query);
        photoAdapter = new PhotoAdapter(photo -> {
            PhotoEntity photoEntity = new PhotoEntity(photo.getHeight(), photo.getWidth(), photo.getPhotographerUrl(), photo.getSrc().getPortrait(), photo.getPhotographer(), false);
            Bundle bundle = new Bundle();
            bundle.putSerializable("photo", photoEntity);
            Navigation.findNavController(requireView()).navigate(R.id.imageFragment, bundle);
        });
        binding.recycler.setAdapter(photoAdapter);
        itemViewModel.itemPagedList.observe(requireActivity(), (Observer<PagedList<Photo>>) items -> photoAdapter.submitList(items));
        return binding.getRoot();
    }
}