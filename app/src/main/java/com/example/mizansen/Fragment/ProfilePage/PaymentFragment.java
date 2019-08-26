package com.example.mizansen.Fragment.ProfilePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mizansen.Adapters.PaymentAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

public class PaymentFragment extends BaseFragment {

    OtherMetod om = new OtherMetod();
    RecyclerView recyclerView;


    public PaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_ProfileSettingFragment";
        recyclerView = view.findViewById(R.id.fragmentpayment_recycler);



        LinearLayoutManager LLM = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(LLM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PaymentAdapter(view.getContext()));



    }


    void getData(){



    }

}