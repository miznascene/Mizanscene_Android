package com.example.mizansen.Fragment.BottomBar.ProfilePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

public class ChildlockFragment extends BaseFragment {

    OtherMetod om = new OtherMetod();
    WebView mWebView;


    public ChildlockFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_childlock, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_ProfileSettingFragment";


        mWebView = view.findViewById(R.id.fragment_childlock_webview);
        mWebView.loadUrl("https://mizanscene.com");


    }


    void getData(){



    }

}
