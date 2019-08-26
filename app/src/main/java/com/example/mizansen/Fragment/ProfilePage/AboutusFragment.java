package com.example.mizansen.Fragment.ProfilePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

public class AboutusFragment extends BaseFragment {

    OtherMetod om = new OtherMetod();



    public AboutusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aboutus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_ProfileSettingFragment";




    }


    void getData(){



    }

}
