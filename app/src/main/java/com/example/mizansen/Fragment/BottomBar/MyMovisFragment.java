package com.example.mizansen.Fragment.BottomBar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mizansen.Adapters.TabFragmentAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Fragment.BottomBar.TabMyMovis.ViewsFragment;
import com.example.mizansen.Fragment.BottomBar.TabMyMovis.WishlistFragment;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.R;
import com.google.android.material.tabs.TabLayout;

public class MyMovisFragment extends BaseFragment {


    RecyclerView courseRecyclerView;
    LanguageHelper languageHelper = new LanguageHelper();
    TabLayout mTabs;
    View mIndicator;
    ViewPager mViewPager;
    FragmentManager fragmentManager;
    int indicatorWidth;
    TabFragmentAdapter adapter;



    public MyMovisFragment(FragmentManager fm) {
        fragmentManager = fm;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mymovis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_MyMovisFragment";


        initView(getView());

        mTabs.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = mTabs.getWidth() / mTabs.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
                Log.i(TAG,"indicatorWidth "+indicatorWidth);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //To move the indicator as the user scroll, we will need the scroll offset values
            //positionOffset is a value from [0..1] which represents how far the page has been scrolled
            //see https://developer.android.com/reference/android/support/v4/view/ViewPager.OnPageChangeListener
            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mIndicator.getLayoutParams();

                //Multiply positionOffset with indicatorWidth to get translation
                float translationOffset =  (positionOffset+i) * indicatorWidth ;
                params.rightMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
                Log.i(TAG,"translationOffset "+translationOffset);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });




    }

    void initView(View view){

        languageHelper.GetLanguage(view.getContext());

        //Assign view reference
        mTabs = view.findViewById(R.id.tab);
        mIndicator = view.findViewById(R.id.indicator);
        mViewPager = view.findViewById(R.id.view_pager_mymovis);

        //Set up the view pager and fragments
        adapter = new TabFragmentAdapter(fragmentManager);
        adapter.addFragment(ViewsFragment.newInstance(), getContext().getResources().getString(R.string.tab_text_2));
        adapter.addFragment(WishlistFragment.newInstance(), getContext().getResources().getString(R.string.tab_text_3));
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);
    }



    @Override
    public void onStart() {
        super.onStart();

        initView(getView());
    }
}