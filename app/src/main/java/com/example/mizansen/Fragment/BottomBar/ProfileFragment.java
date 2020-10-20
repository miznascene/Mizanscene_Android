package com.example.mizansen.Fragment.BottomBar;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mizansen.CustomView.NonSwipeableViewPager;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Fragment.BottomBar.ProfilePage.AboutusFragment;
import com.example.mizansen.Fragment.BottomBar.ProfilePage.ExtendedFragment;
import com.example.mizansen.Fragment.BottomBar.ProfilePage.TransactionFragment;
import com.example.mizansen.Fragment.BottomBar.ProfilePage.ProfileMainFragment;
import com.example.mizansen.Fragment.BottomBar.ProfilePage.ProfileSettingFragment;
import com.example.mizansen.Fragment.BottomBar.ProfilePage.MessageFragment;
import com.example.mizansen.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends BaseFragment {


    ProfilePageAdapter adapter;
    static NonSwipeableViewPager profileViewPager;
    String TAG = "TAG_ProflieActivity";
    static int page = 0;

    public ProfileFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    void initView(View view) {

        profileViewPager = view.findViewById(R.id.profile_viewPager);

        setupViewPager(profileViewPager);

    }

    void setupViewPager(NonSwipeableViewPager viewPager) {

        adapter = new ProfilePageAdapter(getChildFragmentManager());

        ProfileMainFragment profileMainFragment = new ProfileMainFragment();
        ProfileSettingFragment profileSettingFragment = new ProfileSettingFragment();
        TransactionFragment transactionFragment = new TransactionFragment();
        AboutusFragment aboutusFragment = new AboutusFragment();
        MessageFragment messageFragment = new MessageFragment();
        ExtendedFragment extendedFragment = new ExtendedFragment();

        adapter.addFragments(profileMainFragment);
        adapter.addFragments(profileSettingFragment);
        adapter.addFragments(transactionFragment);
        adapter.addFragments(aboutusFragment);
        adapter.addFragments(messageFragment);
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

    public static void setPageByItem(int item) {
        profileViewPager.setCurrentItem(item);
    }


}
