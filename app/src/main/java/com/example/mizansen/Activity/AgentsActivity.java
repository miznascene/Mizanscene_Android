package com.example.mizansen.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Adapters.AgentAdapter;
import com.example.mizansen.R;

public class AgentsActivity extends Activity {


    RecyclerView recyclerView;
    LinearLayoutManager LLM;
    GridLayoutManager gridLayoutManager;
    ImageView backPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents);


        initView();


        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recyclerView.setLayoutManager(LLM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new AgentAdapter(AgentsActivity.this));


    }

    private void initView() {

        recyclerView = findViewById(R.id.agents_recyclerview);
        backPage = findViewById(R.id.agent_backpage);


        LLM = new LinearLayoutManager(AgentsActivity.this);
        gridLayoutManager = new GridLayoutManager(AgentsActivity.this, 3);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
