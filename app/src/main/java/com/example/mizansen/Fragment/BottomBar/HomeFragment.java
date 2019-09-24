package com.example.mizansen.Fragment.BottomBar;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.example.mizansen.Adapters.HomeAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Helper.IndicatorHelper;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Network.ModelNetwork.MainpageModel;
import com.example.mizansen.Network.RequestBuilder;
import com.example.mizansen.Network.RequestBuilderClass;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends BaseFragment {

    RecyclerView recyclerView;
    OtherMetod om = new OtherMetod();
    LanguageHelper languageHelper = new LanguageHelper();
    SwipeRefreshLayout swipeRefreshLayout;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager LLM;
    String pageNumber = "0";
    MainpageModel mainpageModels;
    HomeAdapter homeAdapter;


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

        String Token = om.GetSharedPreferences("Token", "null", getContext());

        Call<MainpageModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).GetMovies("Bearer " + Token, "");
        client.enqueue(new Callback<MainpageModel>() {
            @Override
            public void onResponse(Call<MainpageModel> call, Response<MainpageModel> response) {
                MainpageModel _mainpageModels = response.body();

                swipeRefreshLayout.setRefreshing(false);

                if (pageNumber.equals("0")) {
                    mainpageModels = _mainpageModels;
                    LLM = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(LLM);
                    recyclerView.setHasFixedSize(true);
                    homeAdapter = new HomeAdapter(mainpageModels, getActivity());
                    recyclerView.setAdapter(homeAdapter);
                } else {
                    mainpageModels.data = _mainpageModels.data;

                    homeAdapter.notifyDataSetChanged();
                }

                pageNumber = String.valueOf(Integer.parseInt(pageNumber) + 1);


            }

            @Override
            public void onFailure(Call<MainpageModel> call, Throwable t) {
                Log.i(TAG, "Movies failed: " + t.toString());
                swipeRefreshLayout.setRefreshing(false);
                if (t.toString().equals("timeout"))
                    getData(pageNumber);

            }
        });

    }


}
