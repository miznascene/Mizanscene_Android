package com.example.mizansen.Fragment.BottomBar.ProfilePage;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.R;

import xyz.schwaab.avvylib.AvatarView;

public class ProfileMainFragment extends BaseFragment {


    AvatarView imageUser;

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

        imageUser.setAnimating(false);
        imageUser.setBorderThickness(18);
        imageUser.setHighlightBorderColor(Color.GREEN);
        imageUser.setHighlightBorderColorEnd(Color.GREEN);
        imageUser.setNumberOfArches(0);
        imageUser.setTotalArchesDegreeArea(50);
        

        imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUser.setTotalArchesDegreeArea(imageUser.getTotalArchesDegreeArea()+10);
            }
        });



    }

    void initView(View v) {

        imageUser = v.findViewById(R.id.profilemain_imageuser);

    }

    void getData() {


    }

}
