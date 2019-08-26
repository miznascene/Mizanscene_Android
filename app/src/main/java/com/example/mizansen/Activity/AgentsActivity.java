package com.example.mizansen.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mizansen.Adapters.AgentAdapter;
import com.example.mizansen.R;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class AgentsActivity extends Activity implements AppBarLayout.OnOffsetChangedListener {

    boolean mIsTheTitleVisible = false;
    boolean mIsThePersonDetailsContainerVisible = true;

    static final int ALPHA_ANIMATIONS_DURATION = 200;
    static final float PERCENTAGE_TO_HIDE_PERSON_DETAILS = 0.3f;
    static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;

    TextView titleNameAgent,nameAgent,description;
    AppBarLayout appBarLayout;
    LinearLayout linearLayoutPersonDetails;
    CircleImageView imageViewAgent;
    ImageView imageBackgrund;
    RecyclerView recyclerView;
    boolean status_description = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents);


        initView();

        appBarLayout.addOnOffsetChangedListener(this);

        startAlphaAnimation(titleNameAgent, 0, View.INVISIBLE);

        nameAgent.setText("لیلا اوتادی");
        titleNameAgent.setText("لیلا اوتادی");
        description.setText(getResources().getString(R.string.lorem).substring(0,50)+" ...بیشتر");


        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(status_description){
                   status_description = false;
                   description.setText(getResources().getString(R.string.lorem)+" ...کمتر");
               }else{
                   status_description = true;
                   description.setText(getResources().getString(R.string.lorem).substring(0,50)+" ...بیشتر");
               }
            }
        });

        Picasso.with(AgentsActivity.this)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-G0oh-goskt_vMXWCigeuyGCJ5Y4EahsBKrtjYsxRk7h0-ELvtg")
                .fit()
                .into(imageViewAgent);

        Picasso.with(AgentsActivity.this)
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-G0oh-goskt_vMXWCigeuyGCJ5Y4EahsBKrtjYsxRk7h0-ELvtg")
                .fit()
                .into(imageBackgrund);



        LinearLayoutManager LLM = new LinearLayoutManager(AgentsActivity.this);
        recyclerView.setLayoutManager(LLM);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AgentAdapter(AgentsActivity.this));


    }

    private void initView() {
        titleNameAgent = findViewById(R.id.agents_TitleToolBarname);
        nameAgent = findViewById(R.id.agents_nameagent);
        imageViewAgent = findViewById(R.id.agents_image);
        imageBackgrund = findViewById(R.id.agents_imagebackgrund);
        description = findViewById(R.id.agents_description);
        recyclerView = findViewById(R.id.agents_recyclerview);

        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        linearLayoutPersonDetails = (LinearLayout) findViewById(R.id.linearLayout);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnPersonDetails(percentage);
        handleToolBarTitleVisibility(percentage);
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    private void handleAlphaOnPersonDetails(float percentage) {

        if (percentage >= PERCENTAGE_TO_HIDE_PERSON_DETAILS) {
            if (mIsThePersonDetailsContainerVisible) {
                startAlphaAnimation(linearLayoutPersonDetails, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsThePersonDetailsContainerVisible = false;
            }
        } else {
            if (!mIsThePersonDetailsContainerVisible) {
                startAlphaAnimation(linearLayoutPersonDetails, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsThePersonDetailsContainerVisible = true;
            }
        }
    }

    private void handleToolBarTitleVisibility(float percentage) {

        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(titleNameAgent, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(titleNameAgent, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }
}
