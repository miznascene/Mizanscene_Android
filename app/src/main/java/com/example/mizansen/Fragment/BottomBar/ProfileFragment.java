package com.example.mizansen.Fragment.BottomBar;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mizansen.CustomView.NonSwipeableViewPager;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Fragment.ProfilePage.AboutusFragment;
import com.example.mizansen.Fragment.ProfilePage.ExtendedFragment;
import com.example.mizansen.Fragment.ProfilePage.PaymentFragment;
import com.example.mizansen.Fragment.ProfilePage.ProfileMainFragment;
import com.example.mizansen.Fragment.ProfilePage.ProfileSettingFragment;
import com.example.mizansen.Fragment.ProfilePage.ChildlockFragment;
import com.example.mizansen.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends BaseFragment {


    static TextView titlePage;
    ImageView backPage;
    ProfilePageAdapter adapter;
    static NonSwipeableViewPager profileViewPager;
    String TAG = "TAG_ProflieActivity";
    static int page = 0;
    FragmentManager fragmentManager;

    public ProfileFragment(FragmentManager fm) {
        fragmentManager = fm;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 0) {
                    GotoPage(0, "پروفایل");
                }

            }
        });
    }


    void initView(View view) {

        titlePage = view.findViewById(R.id.profile_titlepage);
        backPage = view.findViewById(R.id.profile_backpage);
        profileViewPager = view.findViewById(R.id.profile_viewPager);

        setupViewPager(profileViewPager);

    }

    void setupViewPager(NonSwipeableViewPager viewPager) {

        adapter = new ProfilePageAdapter(fragmentManager);

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

    public static void GotoPage(int index, String title) {
        page = index;
        profileViewPager.setCurrentItem(index);
        titlePage.setText(title);
    }

}
