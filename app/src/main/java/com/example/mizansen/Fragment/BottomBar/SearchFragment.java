package com.example.mizansen.Fragment.BottomBar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Adapters.AgentAdapter;
import com.example.mizansen.Adapters.SearchAdaptor;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.R;

public class SearchFragment extends BaseFragment {


    LanguageHelper languageHelper = new LanguageHelper();
    RecyclerView recyclerView;
    LinearLayoutManager LLM;
    GridLayoutManager gridLayoutManager;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_SearchFragment";
        initView(getView());


        recyclerView.setLayoutManager(LLM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SearchAdaptor(getContext()));


    }

    void initView(View view) {
        languageHelper.GetLanguage(view.getContext());
        recyclerView = view.findViewById(R.id.fragment_search_recycler);

        LLM = new LinearLayoutManager(getContext());
    }

    void GetData() {

    }

    public static void resultRequst(String Json, Context context) {


    }


}
