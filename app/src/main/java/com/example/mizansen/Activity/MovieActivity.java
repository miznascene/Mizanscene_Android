package com.example.mizansen.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Adapters.AgentMovieAdapter;
import com.example.mizansen.Adapters.CommentAdapter;
import com.example.mizansen.Adapters.GalleryAdapter;
import com.example.mizansen.Adapters.ObjectHomeAdapter;
import com.example.mizansen.NetWork;
import com.example.mizansen.Network.ModelNetwork.IpAddressModel;
import com.example.mizansen.Network.ModelNetwork.MovieArvanCloudModel;
import com.example.mizansen.Network.ModelNetwork.MoviesModel;
import com.example.mizansen.Network.ModelNetwork.TermMoviesModel;
import com.example.mizansen.Network.RequestBuilder;
import com.example.mizansen.Network.RequestBuilderClass;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {


    String TAG = "TAG_MovieActivity";
    OtherMetod om = new OtherMetod();
    FloatingActionButton play, marker;
    RoundedImageView imageview;
    MoviesModel movieModels;
    ImageView image_backgrund, playe_video;
    MovieArvanCloudModel MAC = new MovieArvanCloudModel();
    NetWork nw = new NetWork();
    TextView title, descripton, categorys, moreSerial, moreOtherMovie;
    boolean status_marker = false;
    LinearLayout RatingBar;
    VideoView videoView;
    RecyclerView agentsRecycler, galleryrecycler, otherMoveRecycler, commentMovieRecycler;
    Animation Fede_in, Fede_out;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initView();

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean show = true;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.i(TAG, "verticalOffset :" + verticalOffset);
                if (verticalOffset >= -281 && !show) {
                    imageview.setVisibility(View.VISIBLE);
                    imageview.startAnimation(Fede_in);
                    show = true;

                } else if (verticalOffset <= -320 && show) {
                    imageview.startAnimation(Fede_out);
                    imageview.setVisibility(View.GONE);
                    show = false;
                }

            }
        });

        marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (status_marker) {
                    marker.setImageDrawable(ContextCompat.getDrawable(MovieActivity.this, R.drawable.ic_like_off));
                    status_marker = false;
                } else {
                    marker.setImageDrawable(ContextCompat.getDrawable(MovieActivity.this, R.drawable.ic_like_on));
                    status_marker = true;
                }
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int index = (int) MAC.data.mp4_videos.size() / 2;
                    showMovie(MAC.data.mp4_videos.get(index));
                } catch (Exception e) {
                    Log.i(TAG, "error = " + e.toString());
                }
            }
        });


        RatingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogGrade();
            }
        });

        playe_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoToActivityShowShortMovie("https://as2.cdn.asset.aparat.com/aparat-video/b0b340d17a4ab5e7b3c99759036c64c616486887-360p__21886.mp4");
            }
        });

        moreOtherMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to Page More by id
            }
        });

        moreSerial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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
//        finish();
    }

    void initView() {

        moreSerial = findViewById(R.id.movie_moreserial);
        moreOtherMovie = findViewById(R.id.movie_moreothermovie);

        Fede_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        Fede_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.stoolbar_layout);
        appBarLayout = (AppBarLayout) findViewById(R.id.sapp_bar);

        commentMovieRecycler = findViewById(R.id.movie_commentmovierecycler);
        otherMoveRecycler = findViewById(R.id.movie_othermovie);
        galleryrecycler = findViewById(R.id.movie_galleryrecycler);
        agentsRecycler = findViewById(R.id.movie_agentsrecycler);

        play = findViewById(R.id.movie_fabplaye);
        imageview = findViewById(R.id.movie_imageview);
        image_backgrund = findViewById(R.id.movie_bimage);
        descripton = findViewById(R.id.movie_descripton);
        title = findViewById(R.id.movie_title);
        categorys = findViewById(R.id.movie_category);
        marker = findViewById(R.id.movie_mark);
        RatingBar = findViewById(R.id.movie_reting);
        videoView = findViewById(R.id.movie_videoplyer);
        playe_video = findViewById(R.id.movie_playevideo);


//        Uri uri = Uri.parse("https://as2.cdn.asset.aparat.com/aparat-video/b0b340d17a4ab5e7b3c99759036c64c616486887-360p__21886.mp4");
//
//        videoView.setVideoURI(uri);
//        videoView.seekTo(10);

        Intent in = getIntent();
        Bundle extras = in.getExtras();
        String movie_id = extras.getString("movie_id");
        Log.i(TAG, "movie_id :" + movie_id);
        getDataMovie(movie_id);


        LinearLayoutManager LLM4 = new LinearLayoutManager(MovieActivity.this);
        commentMovieRecycler.setLayoutManager(LLM4);
        commentMovieRecycler.setHasFixedSize(true);
        commentMovieRecycler.setAdapter(new CommentAdapter(MovieActivity.this));

        LinearLayoutManager LLM = new LinearLayoutManager(MovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
        agentsRecycler.setLayoutManager(LLM);
        agentsRecycler.setHasFixedSize(true);
        agentsRecycler.setAdapter(new AgentMovieAdapter(MovieActivity.this));

        LinearLayoutManager LLM2 = new LinearLayoutManager(MovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
        galleryrecycler.setLayoutManager(LLM2);
        galleryrecycler.setHasFixedSize(true);
        galleryrecycler.setAdapter(new GalleryAdapter(MovieActivity.this));


        List<TermMoviesModel> term_movies = new ArrayList<TermMoviesModel>();

        for (int i = 0; i < 10; i++) {
            TermMoviesModel m = new TermMoviesModel();

            m.image = "https://mizanscene.com/wp-content/uploads/2019/08/hamase-ghahreman-wpv_290x430.jpg";
            m.title = "حماسه قهرمانان";
            term_movies.add(m);
        }

        LinearLayoutManager LLM3 = new LinearLayoutManager(MovieActivity.this, LinearLayoutManager.HORIZONTAL, false);
        otherMoveRecycler.setLayoutManager(LLM3);
        otherMoveRecycler.setHasFixedSize(true);
        otherMoveRecycler.setAdapter(new ObjectHomeAdapter(term_movies, MovieActivity.this));


    }

    void getDataMovie(final String movieId) {

        String Token = om.GetSharedPreferences("Token", "null", MovieActivity.this);

        Call<MoviesModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).GetMoviesById("Bearer " + Token, movieId);
        client.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                Log.i(TAG, "Movie received " + response.body());

                movieModels = response.body();

                descripton.setText(movieModels.descripton);
                title.setText(movieModels.title);

                categorys.setText(movieModels.categories.get(0).movie_cat_name);

                try {
                    Picasso.with(MovieActivity.this)
                            .load(movieModels.image)
                            .fit()
                            .into(imageview);

                    Picasso.with(MovieActivity.this)
                            .load(movieModels.image)
                            .fit()
                            .into(image_backgrund);
                } catch (Exception e) {

                }


                for (int i = 1; i < movieModels.categories.size(); i++)
                    categorys.setText(categorys.getText().toString() + "," + movieModels.categories.get(i).movie_cat_name);

                if (movieModels.stream_links.size() > 0)
                    GetIpAddress(movieModels.stream_links.get(0).video_id);
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                Log.i(TAG, "Movie failed: " + t.toString());
                getDataMovie(movieId);

            }
        });


    }

    void GetIpAddress(final String id) {

        Call<IpAddressModel> client = RequestBuilderClass.retrofit_ip.create(RequestBuilder.class).getIpAddress();
        client.enqueue(new Callback<IpAddressModel>() {
            @Override
            public void onResponse(Call<IpAddressModel> call, Response<IpAddressModel> response) {
                Log.i(TAG, "GetIpAddress received " + response.body());


                IpAddressModel ipAddressModel = response.body();
                Log.i(TAG, "Ip Address : " + ipAddressModel.ip_addr);

//                getDataMovieArvanCloud(id, ipAddressModel.ip_addr);
                new NETGET().execute(id, ipAddressModel.ip_addr);

            }

            @Override
            public void onFailure(Call<IpAddressModel> call, Throwable t) {
                Log.i(TAG, "GetIpAddress failed: " + t.toString());

            }
        });


    }

    class NETGET extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... p) {
            return nw.GETDATA("videos/" + p[0] + "?secure_ip=" + p[1]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i(TAG, "onPostExecute NetWork2 result: " + result);
            Gson gson = new Gson();
            Type listType = new TypeToken<MovieArvanCloudModel>() {
            }.getType();
            MAC = gson.fromJson(result.toString(), listType);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MovieActivity.this, MainActivity.class));
        finish();
    }

    void dialogGrade() {
        final Dialog dialog = new Dialog(MovieActivity.this, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_grade);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


}
