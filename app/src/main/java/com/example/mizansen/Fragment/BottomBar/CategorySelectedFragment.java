package com.example.mizansen.Fragment.BottomBar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Activity.AgentsActivity;
import com.example.mizansen.Activity.MainActivity;
import com.example.mizansen.Adapters.AgentAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.R;

public class CategorySelectedFragment extends BaseFragment {



    LanguageHelper languageHelper = new LanguageHelper();
    RecyclerView recyclerView;
    LinearLayoutManager LLM;
    GridLayoutManager gridLayoutManager;
    ImageView backPage;
    static TextView titleCategory;

    public CategorySelectedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categoryselected, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_CategorySelectedFragment";
        initView(getView());


        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.setViewById(1);
            }
        });


        recyclerView.setLayoutManager(LLM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new AgentAdapter(getContext()));


    }

    void initView(View view) {
        languageHelper.GetLanguage(view.getContext());
        recyclerView = view.findViewById(R.id.fragment_categoryselected_recycler);
        backPage = view.findViewById(R.id.fragment_categoryselected_backpage);
        titleCategory = view.findViewById(R.id.fragment_categoryselected_title);

        LLM = new LinearLayoutManager(getContext());
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
    }

    void GetData() {

    }

    public static void resultRequst(String Json, Context context) {


    }

    public static void getIdAndTitleCategory(int Id,String Title){
        titleCategory.setText(Title);

    }


}