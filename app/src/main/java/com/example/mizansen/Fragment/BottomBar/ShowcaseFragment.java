package com.example.mizansen.Fragment.BottomBar;



import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Adapters.ShowcaseAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.Network.ModelNetwork.MainpageModel;
import com.example.mizansen.Network.RequestBuilder;
import com.example.mizansen.Network.RequestBuilderClass;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;
import com.example.mizansen.Slider.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShowcaseFragment extends BaseFragment{

    RecyclerView recyclerView;
    SliderView sliderView;
    OtherMetod om = new OtherMetod();


    public ShowcaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_showcase, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TAG = "TAG_ShowcaseFragment";

        progressBar = view.findViewById(R.id.progressBar);
        container = view.findViewById(R.id.container);
        recyclerView = view.findViewById(R.id.fragment_showcase_recyclerview);

        getData();

    }


    void getData(){

        String Token = om.GetSharedPreferences("Token", "null", getContext());
        Log.i(TAG, "Token is: " + Token);
        Call<List<MainpageModel>> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).GetMovies("Bearer " + Token,"");
        client.enqueue(new Callback<List<MainpageModel>>() {
            @Override
            public void onResponse(Call<List<MainpageModel>> call, Response<List<MainpageModel>> response) {
                Log.i(TAG, "Movies received " + response.body());
                List<MainpageModel> mainpageModels = response.body();

                Log.i(TAG,"term_name :"+mainpageModels.get(0).term_name);
                Log.i(TAG,"term_movies.get(0).title :"+mainpageModels.get(0).term_movies.get(0).title);

//                @SuppressLint("WrongConstant")
                LinearLayoutManager LLM = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(LLM);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(new ShowcaseAdapter(mainpageModels,getActivity()));

            }

            @Override
            public void onFailure(Call<List<MainpageModel>> call, Throwable t) {
                Log.i(TAG, "Movies failed: " + t.toString());
                if(t.toString().equals("timeout"))
                    getData();

            }
        });

    }

}
