package com.example.mizansen.Fragment.BottomBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Fragment.BottomBar.ui.SectionsPagerAdapter;
import com.example.mizansen.R;
import com.google.android.material.tabs.TabLayout;

public class MyVideosFragment extends BaseFragment {

    RecyclerView courseRecyclerView;



    public MyVideosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myvideos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_MyVideosFragment";


//        progressBar = view.findViewById(R.id.progressBar);
        container = view.findViewById(R.id.container);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(), getActivity().getSupportFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);



    }



}