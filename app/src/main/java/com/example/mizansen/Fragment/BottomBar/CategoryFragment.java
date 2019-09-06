package com.example.mizansen.Fragment.BottomBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Adapters.CategoryAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Network.ModelNetwork.CategoryModel;
import com.example.mizansen.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends BaseFragment {

    RecyclerView _RecyclerView;
    CategoryAdapter categoryAdapter;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_CategoryFragment";
        //account = Account.listAll(Account.class).get(0);


//        progressBar = view.findViewById(R.id.progressBar);
        container = view.findViewById(R.id.container);
        _RecyclerView = view.findViewById(R.id.fragment_category_recycler);


        List<CategoryModel> categoryModels = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            CategoryModel cm = new CategoryModel();
            cm.Title = "Action " + i;
            categoryModels.add(cm);
        }


        LinearLayoutManager LLM = new LinearLayoutManager(getContext());
        _RecyclerView.setLayoutManager(LLM);
        _RecyclerView.setHasFixedSize(true);
        categoryAdapter = new CategoryAdapter(categoryModels);
        _RecyclerView.setAdapter(categoryAdapter);


    }


}