package com.example.mizansen.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mizansen.CustomView.NonSwipeableViewPager;
import com.example.mizansen.Fragment.ProfilePage.AboutusFragment;
import com.example.mizansen.Fragment.ProfilePage.ExtendedFragment;
import com.example.mizansen.Fragment.ProfilePage.PaymentFragment;
import com.example.mizansen.Fragment.ProfilePage.ProfileMainFragment;
import com.example.mizansen.Fragment.ProfilePage.ProfileSettingFragment;
import com.example.mizansen.Fragment.ProfilePage.ChildlockFragment;
import com.example.mizansen.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends FragmentActivity {


    static TextView titlePage;
    ImageView backPage;
    ProfilePageAdapter adapter;
    static NonSwipeableViewPager profileViewPager;
    String TAG = "TAG_ProflieActivity";
    static int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initView();

        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page==0){
                    onBackPressed();
                }else{
                    GotoPage(0,"پروفایل");
                }

            }
        });

    }

    void initView(){

        titlePage = findViewById(R.id.profile_titlepage);
        backPage = findViewById(R.id.profile_backpage);
        profileViewPager = findViewById(R.id.profile_viewPager);

        setupViewPager(profileViewPager);

    }

    void setupViewPager(NonSwipeableViewPager viewPager) {

        adapter = new ProfilePageAdapter(getSupportFragmentManager());

        ProfileMainFragment profileMainFragment = new ProfileMainFragment();
        ProfileSettingFragment profileSettingFragment = new ProfileSettingFragment();
        PaymentFragment paymentFragment = new PaymentFragment();
        AboutusFragment aboutusFragment = new AboutusFragment();
        ChildlockFragment childlockFragment = new ChildlockFragment();
        ExtendedFragment extendedFragment = new ExtendedFragment();


        adapter.addFragments(profileMainFragment);
        adapter.addFragments(profileSettingFragment);
        adapter.addFragments(paymentFragment);
        adapter.addFragments(aboutusFragment);
        adapter.addFragments(childlockFragment);
        adapter.addFragments(extendedFragment);


        viewPager.setAdapter(adapter);

    }


    public class ProfilePageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragments = new ArrayList<>();

        public ProfilePageAdapter(FragmentManager fragmentManager) {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        finish();
    }

    public static void GotoPage(int index ,String title){
        page = index;
        profileViewPager.setCurrentItem(index);
        titlePage.setText(title);
    }

}
