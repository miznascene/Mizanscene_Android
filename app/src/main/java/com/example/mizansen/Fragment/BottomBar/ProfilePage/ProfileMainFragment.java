package com.example.mizansen.Fragment.BottomBar.ProfilePage;


import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mizansen.CustomView.CustomBadgeShape;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Fragment.BottomBar.ProfileFragment;
import com.example.mizansen.R;
import com.squareup.picasso.Picasso;

import berlin.volders.badger.BadgeDrawable;
import berlin.volders.badger.Badger;
import berlin.volders.badger.CountBadge;
import xyz.schwaab.avvylib.AvatarView;

public class ProfileMainFragment extends BaseFragment {


    AvatarView imageUser;
    ImageView profileUser, message, subscription, transaction, onlinSupport, phyisicalVersion;
    CountBadge.Factory circleFactory;


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

        onlinSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        phyisicalVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPageByItem(2);
            }
        });

        profileUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPageByItem(1);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPageByItem(4);
            }
        });


    }

    void setPageByItem(int item) {
        ProfileFragment.setPageByItem(item);
    }

    void initView(View v) {

        imageUser = v.findViewById(R.id.profilemain_imageuser);

        profileUser = v.findViewById(R.id.fragment_profilemain_profile);
        message = v.findViewById(R.id.fragment_profilemain_message);
        subscription = v.findViewById(R.id.fragment_profilemain_subscription);
        transaction = v.findViewById(R.id.fragment_profilemain_transaction);
        onlinSupport = v.findViewById(R.id.fragment_profilemain_onlinesupport);
        phyisicalVersion = v.findViewById(R.id.fragment_profilemain_phyisicalversion);


        //***************************************
        imageUser.setAnimating(false);
        imageUser.setBorderThickness(18);
        imageUser.setHighlightBorderColor(Color.GREEN);
        imageUser.setHighlightBorderColorEnd(Color.GREEN);
        imageUser.setNumberOfArches(0);
        imageUser.setTotalArchesDegreeArea(50);

        //Set Badge For imageView Message
        circleFactory = new CountBadge.Factory(v.getContext(), new CustomBadgeShape(v.getContext(), .4f, Gravity.RIGHT | Gravity.TOP));
        BadgeDrawable badge = Badger.sett(message, circleFactory);
        ((CountBadge) badge).setCount(1);

        Picasso.with(getContext())
                .load("https://s3.amazonaws.com/aspph-wp-production/app/uploads/2017/03/Ans--200x200.jpg")
                .fit()
                .into(imageUser);



    }

    void getData() {


    }


}
