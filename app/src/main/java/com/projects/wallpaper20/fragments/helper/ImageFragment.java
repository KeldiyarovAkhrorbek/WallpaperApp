package com.projects.wallpaper20.fragments.helper;

import static android.content.Context.DOWNLOAD_SERVICE;

import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.projects.wallpaper20.MainActivity;
import com.projects.wallpaper20.R;
import com.projects.wallpaper20.database.AppDatabase;
import com.projects.wallpaper20.databinding.BottomDialogBinding;
import com.projects.wallpaper20.databinding.FragmentImageBinding;
import com.projects.wallpaper20.entity.PhotoEntity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

import eightbitlab.com.blurview.RenderScriptBlur;

public class ImageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ImageFragment() {
    }

    public static ImageFragment newInstance(String param1, String param2) {
        ImageFragment fragment = new ImageFragment();
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

    private FragmentImageBinding binding;
    private AppDatabase appDatabase;
    private PhotoEntity photoEntity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentImageBinding.inflate(inflater, container, false);
        appDatabase = AppDatabase.getInstance(requireContext());
        Bundle bundle = getArguments();
        assert bundle != null;
        photoEntity = (PhotoEntity) bundle.getSerializable("photo");
        if (appDatabase.photoDao().isAvailable(photoEntity.getLandscape())) {
            photoEntity = appDatabase.photoDao().getPhotoByUrl(photoEntity.getLandscape());
        }
        Picasso.get().load(photoEntity.getLandscape()).into(binding.image);
        Animation fromTop = AnimationUtils.loadAnimation(requireContext(), R.anim.from_top);
        Animation toTop = AnimationUtils.loadAnimation(requireContext(), R.anim.to_top);
        Animation toBottom = AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom);
        binding.iconBack.startAnimation(fromTop);
        binding.iconShare.startAnimation(fromTop);
        binding.iconAbout.startAnimation(fromTop);
        Animation fromBottom = AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom);
        binding.iconDownload.startAnimation(fromBottom);
        binding.iconPut.startAnimation(fromBottom);
        binding.iconLike.startAnimation(fromBottom);

        if (!photoEntity.getLiked()) {
            binding.imgLike.setImageResource(R.drawable.ic_liked);
            photoEntity.setLiked(false);
        } else {
            binding.imgLike.setImageResource(R.drawable.ic_heart);
            photoEntity.setLiked(true);
        }

        binding.iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.iconDownload.getVisibility() == View.VISIBLE)
                    Navigation.findNavController(requireView()).popBackStack();
                else {
                    binding.iconShare.startAnimation(fromTop);
                    binding.iconAbout.startAnimation(fromTop);
                    binding.iconDownload.startAnimation(fromBottom);
                    binding.iconPut.startAnimation(fromBottom);
                    binding.iconLike.startAnimation(fromBottom);
                    binding.iconHome.startAnimation(toBottom);
                    binding.iconLock.startAnimation(toBottom);
                    binding.iconBoth.startAnimation(toBottom);
                    binding.iconHome.setVisibility(View.GONE);
                    binding.iconLock.setVisibility(View.GONE);
                    binding.iconBoth.setVisibility(View.GONE);
                    binding.iconShare.startAnimation(fromTop);
                    binding.iconAbout.startAnimation(fromTop);
                    binding.iconDownload.startAnimation(fromBottom);
                    binding.iconPut.startAnimation(fromBottom);
                    binding.iconLike.startAnimation(fromBottom);
                    binding.iconShare.setVisibility(View.VISIBLE);
                    binding.iconAbout.setVisibility(View.VISIBLE);
                    binding.iconDownload.setVisibility(View.VISIBLE);
                    binding.iconPut.setVisibility(View.VISIBLE);
                    binding.iconLike.setVisibility(View.VISIBLE);

                }

            }

        });

        binding.iconDownload.setOnClickListener(view -> {
            String url = photoEntity.getLandscape();
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            String title = URLUtil.guessFileName(url, null, null);
            request.setTitle(title);
            request.setDescription("File is now being downloaded...");
            String cookie = CookieManager.getInstance().getCookie(url);
            request.addRequestHeader("cookie", cookie);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_DOWNLOADS,
                    "$title.jpg"
            );
            DownloadManager downloadManager =
                    (DownloadManager) requireActivity().getSystemService(DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
            Toast.makeText(requireContext(), "Downloading...", Toast.LENGTH_SHORT).show();
        });

        binding.iconPut.setOnClickListener(view -> {
            binding.iconShare.startAnimation(toTop);
            binding.iconAbout.startAnimation(toTop);
            binding.iconDownload.startAnimation(toBottom);
            binding.iconPut.startAnimation(toBottom);
            binding.iconLike.startAnimation(toBottom);
            binding.iconShare.setVisibility(View.GONE);
            binding.iconAbout.setVisibility(View.GONE);
            binding.iconDownload.setVisibility(View.GONE);
            binding.iconPut.setVisibility(View.GONE);
            binding.iconLike.setVisibility(View.GONE);
            binding.iconHome.startAnimation(fromBottom);
            binding.iconLock.startAnimation(fromBottom);
            binding.iconBoth.startAnimation(fromBottom);
            binding.iconHome.setVisibility(View.VISIBLE);
            binding.iconLock.setVisibility(View.VISIBLE);
            binding.iconBoth.setVisibility(View.VISIBLE);
        });


        binding.iconShare.setOnClickListener(v -> {
            final Bitmap[] result = {null};
            Picasso.get().load(photoEntity.getLandscape()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    result[0] = bitmap;
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    Toast.makeText(requireContext(), "There was problem loading the photo!", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    binding.progress.setVisibility(View.VISIBLE);
                }
            });
            shareImageBitmap(result[0], requireContext());
        });

        binding.iconAbout.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetStyle);
            BottomDialogBinding bottomDialogBinding = BottomDialogBinding.inflate(getLayoutInflater());
            bottomDialogBinding.websiteTv.setText("Website: pexels.com");
            bottomDialogBinding.authorTv.setText("Author: " + photoEntity.getPhotographer());
            bottomDialogBinding.sizeTv.setText("Size: " + photoEntity.getHeight() + " x " + photoEntity.getWidth());
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            bottomSheetDialog.setCancelable(true);
            bottomSheetDialog.setContentView(bottomDialogBinding.getRoot());
            float radius = 25;
            bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            bottomDialogBinding.blurViewDialog.setupWith(container)
                    .setBlurAlgorithm(new RenderScriptBlur(requireContext()))
                    .setBlurRadius(radius)
                    .setBlurAutoUpdate(true)
                    .setHasFixedTransformationMatrix(true);
            bottomSheetDialog.show();
        });

        binding.iconLike.setOnClickListener(view -> {
            if (photoEntity.getLiked()) {
                binding.imgLike.setImageResource(R.drawable.ic_liked);
                photoEntity.setLiked(false);
                if (appDatabase.photoDao().isAvailable(photoEntity.getLandscape()))
                    appDatabase.photoDao().deletePhoto(photoEntity);
            } else {
                binding.imgLike.setImageResource(R.drawable.ic_heart);
                photoEntity.setLiked(true);
                appDatabase.photoDao().addPhoto(photoEntity);
            }
        });

        binding.iconHome.setOnClickListener(v -> {
            final Bitmap[] result = {null};
            Picasso.get().load(photoEntity.getLandscape()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    result[0] = bitmap;
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    Toast.makeText(requireContext(), "There was problem loading the photo!", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    binding.progress.setVisibility(View.VISIBLE);
                }
            });
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(requireContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                try {
                    wallpaperManager.setBitmap(result[0], null, false, WallpaperManager.FLAG_SYSTEM);
                    Toast.makeText(requireContext(), "Successfully set", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        binding.iconLock.setOnClickListener(v -> {
            final Bitmap[] result = {null};
            Picasso.get().load(photoEntity.getLandscape()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    result[0] = bitmap;
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    Toast.makeText(requireContext(), "There was problem loading the photo!", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    binding.progress.setVisibility(View.VISIBLE);
                }
            });
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(requireContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                try {
                    wallpaperManager.setBitmap(result[0], null, false, WallpaperManager.FLAG_LOCK);
                    Toast.makeText(requireContext(), "Successfully set", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        binding.iconBoth.setOnClickListener(v -> {
            final Bitmap[] result = {null};
            Picasso.get().load(photoEntity.getLandscape()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    result[0] = bitmap;
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    Toast.makeText(requireContext(), "There was problem loading the photo!", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    binding.progress.setVisibility(View.VISIBLE);
                }
            });
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(requireContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                try {
                    wallpaperManager.setBitmap(result[0], null, false, WallpaperManager.FLAG_LOCK | WallpaperManager.FLAG_SYSTEM);
                    Toast.makeText(requireContext(), "Successfully set", Toast.LENGTH_SHORT).show();
                    binding.progress.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        return binding.getRoot();
    }

    private void shareImageBitmap(Bitmap bitmap, Context context) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, photoEntity.getLandscape());
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);

        Intent intent = new Intent(Intent.ACTION_SEND).setType("image/*");

        Uri uri = bitmapToUri(bitmap, context);

        // putting uri of image to be shared
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        // adding text to share
        intent.putExtra(
                Intent.EXTRA_TEXT,
                "Amazing wallpaper for your device\nPowered by Team #34"
        );

        // Add subject Here
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");


        // calling startActivity() to share
        context.startActivity(Intent.createChooser(intent, "Share Via"));
    }

    private Uri bitmapToUri(Bitmap bitmap, Context context) {
        return Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "image", null));
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).hideBlurView();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        ((MainActivity) getActivity()).showBlurView();
    }
}