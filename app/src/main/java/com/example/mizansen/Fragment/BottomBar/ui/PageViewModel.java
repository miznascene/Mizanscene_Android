package com.example.mizansen.Fragment.BottomBar.ui;

import android.view.View;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Adapters.AgentAdapter;

public class PageViewModel extends ViewModel {

    private MutableLiveData<String> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<String, String>() {
        @Override
        public String apply(String input) {
            return input;
        }
    });

    public void setIndex(String index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void GetRecyclerView(View view, int path,int index) {
        RecyclerView recyclerView = view.findViewById(path);
        LinearLayoutManager LLM = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(LLM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AgentAdapter(recyclerView.getContext()));

    }
}