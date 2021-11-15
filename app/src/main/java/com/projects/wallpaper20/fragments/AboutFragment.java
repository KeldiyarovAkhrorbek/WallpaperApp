package com.projects.wallpaper20.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.projects.wallpaper20.MainActivity;
import com.projects.wallpaper20.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AboutFragment() {
    }

    private FragmentAboutBinding binding;

    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).hideOnlyBlurView();
    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity) getActivity()).showOnlyBlurView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}