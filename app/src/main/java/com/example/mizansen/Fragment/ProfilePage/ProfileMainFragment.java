package com.example.mizansen.Fragment.ProfilePage;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mizansen.Fragment.BottomBar.ProfileFragment;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

public class ProfileMainFragment extends BaseFragment {

    OtherMetod om = new OtherMetod();
    LinearLayout profileSetting, payment, aboutus, childlock;
    Button extended;


    public ProfileMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profilemain, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_ProfileMainFragment";

        initView(view);

        profileSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment.GotoPage(1, "ویرایش پروفایل");
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment.GotoPage(2, "پرداخت ها");
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment.GotoPage(3, "درباره ما");
            }
        });

        childlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment.GotoPage(4, "قفل کودک");
            }
        });

        extended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment.GotoPage(5, "تمدید");
            }
        });
    }

    void initView(View v) {
        profileSetting = v.findViewById(R.id.profilemain_setting);
        payment = v.findViewById(R.id.profilemain_payment);
        aboutus = v.findViewById(R.id.profilemain_aboutus);
        childlock = v.findViewById(R.id.profilemain_childlock);
        extended = v.findViewById(R.id.profilemain_extended);
    }

    void getData() {


    }

}
