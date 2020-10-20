package com.example.mizansen.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mizansen.Adapters.CommentAdapter;
import com.example.mizansen.Adapters.GalleryAdapter;
import com.example.mizansen.Adapters.ObjectHomeAdapter;
import com.example.mizansen.Adapters.TabFragmentAdapter;
import com.example.mizansen.Fragment.TabSeasonFragment;
import com.example.mizansen.Helper.JsonHelper;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.Helper.RequestHelper;
import com.example.mizansen.Helper.SharedPreferencesHelper;
import com.example.mizansen.Network.ModelNetwork.IpAddressModel;
import com.example.mizansen.Network.ModelNetwork.TermMoviesModel;
import com.example.mizansen.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends FragmentActivity {


    String TAG = "TAG_MovieActivity", movie_id, Token;
    ImageView backPage, poster;
    LanguageHelper languageHelper = new LanguageHelper();
    static RequestHelper requestHelper = new RequestHelper();
    TextView Actor1;
    RecyclerView recyclerSimilarMovie, recyclerComment, recyclerGallery;
    TabLayout mTabs;
    View mIndicator;
    ViewPager mViewPager;
    TabFragmentAdapter adapter;
    int indicatorWidth;
    FloatingActionButton playeMovie;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initView();

        Actor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MovieActivity.this, AgentsActivity.class));

            }
        });

        backPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome();
            }
        });


        mTabs.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = mTabs.getWidth() / mTabs.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
//                Log.i(TAG,"indicatorWidth "+indicatorWidth);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //To move the indicator as the user scroll, we will need the scroll offset values
            //positionOffset is a value from [0..1] which represents how far the page has been scrolled
            //see https://developer.android.com/reference/android/support/v4/view/ViewPager.OnPageChangeListener
            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();

                //Multiply positionOffset with indicatorWidth to get translation
                float translationOffset = (positionOffset + i) * indicatorWidth;
                params.rightMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
//                Log.i(TAG,"translationOffset "+translationOffset);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        playeMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMovie("https://hw5.cdn.asset.aparat.com/aparat-video/f03c701a6362eedc8036213fdd42bdd117523323-360p__21465.mp4");
            }
        });


    }

    void goToHome() {
        startActivity(new Intent(MovieActivity.this, MainActivity.class));
        finish();
    }

    void GoToActivityShowShortMovie(String link) {

        Intent i = new Intent(MovieActivity.this, ShowShortMovieActivity.class);
        i.putExtra("LinkShortMovie", link);

        startActivity(i);

    }

    void showMovie(String Link) {
        Intent i = new Intent(MovieActivity.this, ShowmovieActivity.class);
        i.putExtra("Link", Link);
        startActivity(i);
    }

    void initView() {

        Actor1 = findViewById(R.id.movie_actor1);
        backPage = findViewById(R.id.movie_backpage);
        recyclerSimilarMovie = findViewById(R.id.movie_SimilarMovie);
        mTabs = findViewById(R.id.movie_tabSeason);
        mIndicator = findViewById(R.id.movie_indicatorSeason);
        mViewPager = findViewById(R.id.movie_viewSeason);
        recyclerComment = findViewById(R.id.movie_comment);
        recyclerGallery = findViewById(R.id.movie_gallery);
        playeMovie = findViewById(R.id.movie_playmovie);
        poster = findViewById(R.id.movie_posterimage);

        languageHelper.GetLanguage(MovieActivity.this);
        Token = SharedPreferencesHelper.GetSharedPreferences("Token", "null", MovieActivity.this);

        Bundle bundle = getIntent().getExtras();
        movie_id = bundle.getString("movie_id");
        Log.i(TAG, "movie_id" + movie_id);
        requestHelper.movieDataById(Token, getResources().getString(R.string.API_Movie) + movie_id, MovieActivity.this);


        //Set up the view pager and fragments
        adapter = new TabFragmentAdapter(getSupportFragmentManager());

        getData();

        Picasso.with(MovieActivity.this)
                .load("https://upload.wikimedia.org/wikipedia/fa/7/73/Samurai_in_Berlin.jpg")
                .fit()
                .into(poster);

    }

    void getData() {


        recyclerGallery.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MovieActivity.this, 2);
        recyclerGallery.setLayoutManager(gridLayoutManager);
        recyclerGallery.setAdapter(new GalleryAdapter(MovieActivity.this));


        List<TermMoviesModel> term_movies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TermMoviesModel t = new TermMoviesModel();
            t.title = "Title" + i;
            t.image = "https://www.uptvs.net/wp-content/uploads/2018/04/texas-min.jpg";
            t.descripton = "descripton" + i;
            t.id = String.valueOf(i + 1);
            term_movies.add(t);
        }

        LinearLayoutManager LLM = new LinearLayoutManager(MovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerSimilarMovie.setLayoutManager(LLM);
        recyclerSimilarMovie.setHasFixedSize(true);
        recyclerSimilarMovie.setAdapter(new ObjectHomeAdapter(term_movies, MovieActivity.this));


        adapter.addFragment(TabSeasonFragment.newInstance(), getResources().getString(R.string.Season) + "1");
        adapter.addFragment(TabSeasonFragment.newInstance(), getResources().getString(R.string.Season) + "2");
        adapter.addFragment(TabSeasonFragment.newInstance(), getResources().getString(R.string.Season) + "3");
        mViewPager.setAdapter(adapter);
        mTabs.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(0);


        LinearLayoutManager LLMComment = new LinearLayoutManager(MovieActivity.this);
        recyclerComment.setLayoutManager(LLMComment);
        recyclerComment.setHasFixedSize(true);
        recyclerComment.setAdapter(new CommentAdapter(MovieActivity.this));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MovieActivity.this, MainActivity.class));
        finish();
    }

    public static void ResualtDataMovie(String Json, Context context) {
        requestHelper.IpNetWork(context.getResources().getString(R.string.API_IP), context);
    }

    public static void ResualtDataIp(String Json, Context context) {

        IpAddressModel ipAddressModel = JsonHelper.ConvertStringToIpAddressModel(Json);
//        String url = context.getResources().getString(R.string.API_ArvanDataMovie)+"id_movie_Arvan"+"?secure_ip=" +ipAddressModel.ip_addr;
//        requestHelper.ArvanMovie(context.getResources().getString(R.string.API_KeyArvan),url,context);


    }


}
