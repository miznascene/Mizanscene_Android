package com.example.mizansen.Fragment.ProfilePage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

public class ProfileSettingFragment extends BaseFragment {

    OtherMetod om = new OtherMetod();
    LinearLayout changePassword, changeUsername, outAccunt;
    Context context;

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

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogChangePassword();
            }
        });

        changeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogChangeUsername();
            }
        });

        outAccunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogOutAccunt();
            }
        });

    }

    void dialogChangePassword() {
        final Dialog dialog = new Dialog(context, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_changepassword);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    void dialogChangeUsername() {
        final Dialog dialog = new Dialog(context, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_changeusername);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    void dialogOutAccunt() {
        final Dialog dialog = new Dialog(context, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_outputaccunt);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


    void initView(View view) {

        changePassword = view.findViewById(R.id.fragment_progilesetting_changepassword);
        changeUsername = view.findViewById(R.id.fragment_progilesetting_changeusername);
        outAccunt = view.findViewById(R.id.fragment_progilesetting_outaccunt);

    }


    void getData() {


    }

}
