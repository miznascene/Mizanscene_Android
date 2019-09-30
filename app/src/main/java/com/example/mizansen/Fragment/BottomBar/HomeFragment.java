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

import com.example.mizansen.Adapters.HomeAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Helper.JsonHelper;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.RequestHelper;
import com.example.mizansen.Network.ModelNetwork.MainpageModel;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;


public class HomeFragment extends BaseFragment {

    static RecyclerView recyclerView;
    OtherMetod om = new OtherMetod();
    LanguageHelper languageHelper = new LanguageHelper();
    static SwipeRefreshLayout swipeRefreshLayout;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    static LinearLayoutManager LLM;
    static String pageNumber = "0";
    static MainpageModel mainpageModels;
    static HomeAdapter homeAdapter;
    RequestHelper requestHelper = new RequestHelper();


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_HomeFragment";


        recyclerView = view.findViewById(R.id.fragment_home_recyclerview);
        swipeRefreshLayout = view.findViewById(R.id.fragment_home_refresh);
        LLM = new LinearLayoutManager(getContext());
        languageHelper.GetLanguage(view.getContext());

        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("0");
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {

                    visibleItemCount = LLM.getChildCount();
                    totalItemCount = LLM.getItemCount();
                    pastVisiblesItems = LLM.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        Log.i(TAG, "check for scroll down");
//                        swipeRefreshLayout.setRefreshing(true);
//                        getData(pageNumber);
                    }


                }
            }
        });

        getData("0");


    }


    void getData(String PageNumber) {
        pageNumber = PageNumber;

        String Token = "Bearer " + om.GetSharedPreferences("Token", "null", getContext());

        requestHelper.HomePage(Token, getContext().getResources().getString(R.string.API_HomePage), getContext(), pageNumber);


    }

    public static void resultRequst(String Json, Context context) {

        MainpageModel _mainpageModel = JsonHelper.ConvertStringToMainpageModel(Json);
        swipeRefreshLayout.setRefreshing(false);

        if (pageNumber.equals("0")) {
            mainpageModels = _mainpageModel;

            recyclerView.setLayoutManager(LLM);
            recyclerView.setHasFixedSize(true);
            homeAdapter = new HomeAdapter(mainpageModels, context);
            recyclerView.setAdapter(homeAdapter);
        } else {
            mainpageModels.data = _mainpageModel.data;

            homeAdapter.notifyDataSetChanged();
        }

        pageNumber = String.valueOf(Integer.parseInt(pageNumber) + 1);

    }

}
