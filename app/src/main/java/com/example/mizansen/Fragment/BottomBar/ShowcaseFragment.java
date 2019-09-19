package com.example.mizansen.Fragment.BottomBar;




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


public class ShowcaseFragment extends BaseFragment {

    RecyclerView recyclerView;
    OtherMetod om = new OtherMetod();
    LanguageHelper languageHelper = new LanguageHelper();


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


        container = view.findViewById(R.id.container);
        recyclerView = view.findViewById(R.id.fragment_showcase_recyclerview);
        languageHelper.GetLanguage(view.getContext());
        getData(view);

    }


    void getData(final View view) {

        String Token = om.GetSharedPreferences("Token", "null", getContext());

        IndicatorHelper indicatorHelper = new IndicatorHelper();

        indicatorHelper.CreateIndicator(view.getContext());
        Call<MainpageModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).GetMovies("Bearer " + Token, "");
        client.enqueue(new Callback<MainpageModel>() {
            @Override
            public void onResponse(Call<MainpageModel> call, Response<MainpageModel> response) {
                MainpageModel mainpageModels = response.body();

                indicatorHelper.DismissIndicator();
//                Log.i(TAG, "term_name :" + mainpageModels.terms.get(0).term_name);
//                Log.i(TAG, "term_movies.get(0).title :" + mainpageModels.terms.get(0).term_movies.get(0).title);
//                Log.i(TAG, "mainpageModels.slideshow.get(0).slideshow_item_id:" + mainpageModels.slideshow.get(0).slideshow_item_id);

//                @SuppressLint("WrongConstant")
                LinearLayoutManager LLM = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(LLM);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(new ShowcaseAdapter(mainpageModels, getActivity()));

            }

            @Override
            public void onFailure(Call<MainpageModel> call, Throwable t) {
                Log.i(TAG, "Movies failed: " + t.toString());
                indicatorHelper.DismissIndicator();
                if (t.toString().equals("timeout"))
                    getData(view);

            }
        });

    }

}
