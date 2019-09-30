package com.example.mizansen.Fragment.BottomBar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mizansen.Adapters.CategoryAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Helper.JsonHelper;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.RequestHelper;
import com.example.mizansen.Helper.SharedPreferencesHelper;
import com.example.mizansen.Helper.ValidationHelper;
import com.example.mizansen.Network.ModelNetwork.CategoryModel;
import com.example.mizansen.R;


public class CategoryFragment extends BaseFragment {

    static RecyclerView _RecyclerView;
    static CategoryAdapter categoryAdapter;
    LanguageHelper languageHelper = new LanguageHelper();
    static CategoryModel categoryModels = new CategoryModel();
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    static LinearLayoutManager LLM;
    RequestHelper requestHelper = new RequestHelper();
    static SwipeRefreshLayout swipeRefreshLayout;


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
        swipeRefreshLayout = view.findViewById(R.id.fragment_home_refresh);
        LLM = new LinearLayoutManager(getContext());

        swipeRefreshLayout.setRefreshing(true);
        GetData();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetData();
            }
        });


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


    void GetData() {
        String Token = "Bearer " + SharedPreferencesHelper.GetSharedPreferences("Token", "null", getContext());
        requestHelper.CategoryByPagenaumbr(Token, getContext().getResources().getString(R.string.API_Category), getContext(), "0");

    }

    public static void resultRequst(String Json, Context context) {
        swipeRefreshLayout.setRefreshing(false);
        CategoryModel categoryModel = JsonHelper.ConvertStringToCategoryModel(Json);

        if (ValidationHelper.validStatus(categoryModel.status)) {
            categoryModels = categoryModel;
            _RecyclerView.setLayoutManager(LLM);
            _RecyclerView.setHasFixedSize(true);
            categoryAdapter = new CategoryAdapter(categoryModels, context);
            _RecyclerView.setAdapter(categoryAdapter);
        } else {

        }


    }


}