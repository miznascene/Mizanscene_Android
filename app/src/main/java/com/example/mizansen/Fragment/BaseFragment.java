package com.example.mizansen.Fragment;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.example.mizansen.Activity.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */

public abstract class BaseFragment extends Fragment {

    public String TAG = "TAG_BASE_FRAGMENT";
    protected Account account = null;
    public boolean shouldBackOnLogin = true;
    protected ConstraintLayout container;
    protected ProgressBar progressBar;
    private float fadedAlpha = 0.35f;

    public BaseFragment() {
        // Required empty public constructor
    }


    protected void shortToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void longToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    protected void inputFieldToast(String fieledName){
        String message = "لطفا " + fieledName + " را وارد کنید";
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


    protected void gotoLogin(){
        Intent i = new Intent(getContext(), LoginActivity.class);
        if(shouldBackOnLogin){
            i.putExtra("back", true);
            startActivity(i);
        }
        else {
            i.putExtra("back", false);
            startActivity(i);
            getActivity().finish();
        }

    }


    protected void login(final boolean startLoginActivity) {

        fade();


    }


    protected void fade(){
        if(container != null && progressBar != null){

            container.setEnabled(false);
            for ( int i = 0; i < container.getChildCount();  i++ ){
                View view = container.getChildAt(i);
                view.setEnabled(false); // Or whatever you want to do with the view.
            }


            container.setAlpha(fadedAlpha);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setEnabled(true);
        }
    }

    protected void unFade(){
        if(container != null && progressBar != null){

            container.setEnabled(false);
            for ( int i = 0; i < container.getChildCount();  i++ ){
                View view = container.getChildAt(i);
                view.setEnabled(true); // Or whatever you want to do with the view.
            }

            container.setAlpha(1);
            progressBar.setVisibility(View.INVISIBLE);
            progressBar.setEnabled(false);
        }
    }

}
