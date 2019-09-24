package com.example.mizansen.Fragment.BottomBar;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Activity.MainActivity;
import com.example.mizansen.Adapters.CategoryAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Network.ModelNetwork.CategoryModel;
import com.example.mizansen.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends BaseFragment {

    RecyclerView _RecyclerView;
    CategoryAdapter categoryAdapter;
    LanguageHelper languageHelper = new LanguageHelper();
    List<CategoryModel> categoryModels = new ArrayList<>();
    int pastVisiblesItems, visibleItemCount, totalItemCount;


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

        languageHelper.GetLanguage(view.getContext());

        _RecyclerView = view.findViewById(R.id.fragment_category_recycler);


        for (int i = 0; i < 18; i++) {
            CategoryModel cm = new CategoryModel();
            cm.Title = "عنوان " + i;
            categoryModels.add(cm);
        }


        LinearLayoutManager LLM = new LinearLayoutManager(getContext());
        _RecyclerView.setLayoutManager(LLM);
        _RecyclerView.setHasFixedSize(true);
        categoryAdapter = new CategoryAdapter(categoryModels,view.getContext());
        _RecyclerView.setAdapter(categoryAdapter);


        _RecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {

                    visibleItemCount = LLM.getChildCount();
                    totalItemCount = LLM.getItemCount();
                    pastVisiblesItems = LLM.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        Log.i(TAG, "check for scroll down");
                    }


                }
            }
        });


    }


}