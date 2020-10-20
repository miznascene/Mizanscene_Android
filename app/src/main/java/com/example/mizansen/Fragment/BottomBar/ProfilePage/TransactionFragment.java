package com.example.mizansen.Fragment.BottomBar.ProfilePage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mizansen.Adapters.TransactionAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Fragment.BottomBar.ProfileFragment;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

public class TransactionFragment extends BaseFragment {

    OtherMetod om = new OtherMetod();
    RecyclerView recyclerView;
    ImageView backPage;
    LinearLayoutManager LLM;

    public TransactionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_ProfileSettingFragment";

        initView(getView());

        recyclerView.setLayoutManager(LLM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new TransactionAdapter(view.getContext()));

        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment.setPageByItem(0);
            }
        });



    }

    void initView(View view){
        backPage = view.findViewById(R.id.fragment_transaction_backpage);
        recyclerView = view.findViewById(R.id.fragmentpayment_recycler);
        LLM = new LinearLayoutManager(view.getContext());
    }

    void getData(){



    }

}