package com.projects.wallpaper20;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.projects.wallpaper20.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private BlurView blurView;
    private DrawerLayout drawer;
    private NavController navController;
    private NavigationView navigationView;

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        drawer = binding.drawerLayout;
        navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_popular,
                R.id.nav_random,
                R.id.nav_liked,
                R.id.nav_about)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

        binding.appBarMain.contentMain.dotHome.setVisibility(View.VISIBLE);


        binding.appBarMain.contentMain.homeIcon.setOnClickListener(v -> {
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_home);
            setVisibility(0);
        });
        binding.appBarMain.contentMain.popularIcon.setOnClickListener(v -> {
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_popular);
            setVisibility(1);
        });
        binding.appBarMain.contentMain.randomIcon.setOnClickListener(v -> {
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_random);
            setVisibility(2);
        });
        binding.appBarMain.contentMain.likedIcon.setOnClickListener(v -> {
            Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_liked);
            setVisibility(3);
        });


        float radius = 10;

        View decorView = getWindow().getDecorView();
        ViewGroup rootView = decorView.findViewById(android.R.id.content);
        Drawable windowBackground = decorView.getBackground();
        blurView = findViewById(R.id.blurView);
        blurView.setupWith(rootView)
                .setFrameClearDrawable(windowBackground)
                .setBlurAlgorithm(new RenderScriptBlur(this))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true);

        navigationView.setItemIconTintList(null);

    }

    public void setVisibility(int count) {
        binding.appBarMain.contentMain.dotHome.setVisibility(View.GONE);
        binding.appBarMain.contentMain.dotPopular.setVisibility(View.GONE);
        binding.appBarMain.contentMain.dotRandom.setVisibility(View.GONE);
        binding.appBarMain.contentMain.dotLiked.setVisibility(View.GONE);
        switch (count) {
            case 0:
                binding.appBarMain.contentMain.dotHome.setVisibility(View.VISIBLE);
                break;
            case 1:
                binding.appBarMain.contentMain.dotPopular.setVisibility(View.VISIBLE);
                break;
            case 2:
                binding.appBarMain.contentMain.dotRandom.setVisibility(View.VISIBLE);
                break;
            case 3:
                binding.appBarMain.contentMain.dotLiked.setVisibility(View.VISIBLE);
                break;
        }
    }


    public void hideBlurView() {
        blurView.setVisibility(View.GONE);
        getSupportActionBar().hide();
    }


    public void showBlurView() {
        blurView.setVisibility(View.VISIBLE);
        getSupportActionBar().show();
    }


    public void hideOnlyBlurView() {
        blurView.setVisibility(View.GONE);
    }


    public void showOnlyBlurView() {
        blurView.setVisibility(View.VISIBLE);
    }

    public void hideActionBar() {
        getSupportActionBar().hide();
    }

    public void showActionBar() {
        getSupportActionBar().show();
    }


    @Override
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                setVisibility(0);
                break;
            case R.id.nav_popular:
                setVisibility(1);
                break;
            case R.id.nav_random:
                setVisibility(2);
                break;
            case R.id.nav_liked:
                setVisibility(3);
                break;
        }
        NavigationUI.onNavDestinationSelected(item, navController);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}