package com.example.mizansen.Activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.mizansen.CustomView.NonSwipeableViewPager;
import com.example.mizansen.Fragment.BottomBar.CategoryFragment;
import com.example.mizansen.Fragment.BottomBar.MyVideosFragment;
import com.example.mizansen.Fragment.BottomBar.ShowcaseFragment;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends FragmentActivity {

    BottomNavigationView navigation;
    BottomBarAdapter adapter;
    public NonSwipeableViewPager viewPager;
    CircleImageView imageView_profile;
    String TAG = "TAG_MainActivity";



    LanguageHelper languageHelper = new LanguageHelper();


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }

    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.action_Showcase:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.action_category:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.action_myvideos:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();


        imageView_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                finish();
            }
        });


    }

    @SuppressLint({"ResourceType", "WrongViewCast"})
    void initView(){

        imageView_profile = findViewById(R.id.main_profileimage);
        navigation = (BottomNavigationView) findViewById(R.id.BottomNavigationView);
        navigation.setSelectedItemId(R.menu.my_navigation_items);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        languageHelper.GetLanguage(MainActivity.this);


        viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

    }

    void setupViewPager(NonSwipeableViewPager viewPager) {

        adapter = new BottomBarAdapter(getSupportFragmentManager());

        ShowcaseFragment showcaseFragment = new ShowcaseFragment();
        CategoryFragment categoryFragment = new CategoryFragment();
        MyVideosFragment myVideosFragment = new MyVideosFragment();

        adapter.addFragments(showcaseFragment);
        adapter.addFragments(categoryFragment);
        adapter.addFragments(myVideosFragment);

        viewPager.setAdapter(adapter);

    }


    public class BottomBarAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();

        public BottomBarAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Our custom method that populates this Adapter with Fragments
        public void addFragments(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

}
