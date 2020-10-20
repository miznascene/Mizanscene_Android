package com.example.mizansen.Fragment.BottomBar.ProfilePage;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Fragment.BottomBar.ProfileFragment;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;


public class ProfileSettingFragment extends BaseFragment {

    OtherMetod om = new OtherMetod();
    Context context;
    ImageView backPage;
    TextView birthday;

    public ProfileSettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profilesetting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = view.getContext();
        TAG = "TAG_ProfileSettingFragment";
        initView(view);

        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment.setPageByItem(0);
            }
        });

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SpinnerDatePickerDialogBuilder()
                        .context(getContext())
                        .spinnerTheme(R.style.NumberPickerStyle)
                        .showTitle(true)
                        .showDaySpinner(true)
                        .defaultDate(2019, 10, 5)
                        .maxDate(2020, 0, 1)
                        .minDate(1900, 0, 1)
                        .build()
                        .show();
            }
        });



    }


    void initView(View view) {
        backPage = view.findViewById(R.id.fragment_profilesetting_backpage);
        birthday = view.findViewById(R.id.fragment_profilesetting_birthday);

    }


    void getData() {


    }

}
