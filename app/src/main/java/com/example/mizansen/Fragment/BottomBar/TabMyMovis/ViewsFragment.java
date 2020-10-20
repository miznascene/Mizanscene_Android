package com.example.mizansen.Fragment.BottomBar.TabMyMovis;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Adapters.AgentAdapter;
import com.example.mizansen.Fragment.BaseFragment;
import com.example.mizansen.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ViewsFragment extends BaseFragment {

    FloatingActionButton floatingActionDown;
    RecyclerView recyclerView;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    GridLayoutManager gridLayoutManager;

    public static ViewsFragment newInstance() {
        return new ViewsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_views, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

        floatingActionDown.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

                recyclerView.smoothScrollToPosition(10);
                floatingActionDown.setVisibility(View.GONE);

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {

                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        Log.i(TAG, "check for scroll down");
                        floatingActionDown.setVisibility(View.GONE);
                    } else {
                        floatingActionDown.setVisibility(View.VISIBLE);
                    }


                } else {
                    floatingActionDown.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    void initView() {

        recyclerView = getView().findViewById(R.id.views_recycler);
        floatingActionDown = getView().findViewById(R.id.views_floating);

        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new AgentAdapter(getContext()));
        Log.i(TAG, "initView ViewsFragment");

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
