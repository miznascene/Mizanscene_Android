package com.example.mizansen.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.NetWork;
import com.example.mizansen.Network.ModelNetwork.MovieArvanCloudModel;
import com.example.mizansen.Network.ModelNetwork.MoviesModel;
import com.example.mizansen.OtherClass.OtherMetod;
import com.example.mizansen.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.makeramen.roundedimageview.RoundedImageView;

public class MovieActivity extends Activity {


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
    LanguageHelper languageHelper = new LanguageHelper();


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initView();






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
        languageHelper.GetLanguage(MovieActivity.this);


    }

//    void getDataMovie(final String movieId) {
//
//        String Token = om.GetSharedPreferences("Token", "null", MovieActivity.this);
//
//        Call<MoviesModel> client = RequestBuilderClass.retrofit.create(RequestBuilder.class).GetMoviesById("Bearer " + Token, movieId);
//        client.enqueue(new Callback<MoviesModel>() {
//            @Override
//            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
//                Log.i(TAG, "Movie received " + response.body());
//
//                movieModels = response.body();
//
//                descripton.setText(movieModels.descripton);
//                title.setText(movieModels.title);
//
//                categorys.setText(movieModels.categories.get(0).data.get(0).term_name);
//
//                try {
//                    Picasso.with(MovieActivity.this)
//                            .load(movieModels.image)
//                            .fit()
//                            .into(imageview);
//
//                    Picasso.with(MovieActivity.this)
//                            .load(movieModels.image)
//                            .fit()
//                            .into(image_backgrund);
//                } catch (Exception e) {
//
//                }
//
//
//                for (int i = 1; i < movieModels.categories.size(); i++)
//                    categorys.setText(categorys.getText().toString() + "," + movieModels.categories.get(i).data.get(0).term_name);
//
//                if (movieModels.stream_links.size() > 0)
//                    GetIpAddress(movieModels.stream_links.get(0).video_id);
//            }
//
//            @Override
//            public void onFailure(Call<MoviesModel> call, Throwable t) {
//                Log.i(TAG, "Movie failed: " + t.toString());
//                getDataMovie(movieId);
//
//            }
//        });
//
//
//    }

//    void GetIpAddress(final String id) {
//
//        Call<IpAddressModel> client = RequestBuilderClass.retrofit_ip.create(RequestBuilder.class).getIpAddress();
//        client.enqueue(new Callback<IpAddressModel>() {
//            @Override
//            public void onResponse(Call<IpAddressModel> call, Response<IpAddressModel> response) {
//                Log.i(TAG, "GetIpAddress received " + response.body());
//
//
//                IpAddressModel ipAddressModel = response.body();
//                Log.i(TAG, "Ip Address : " + ipAddressModel.ip_addr);
//
////                getDataMovieArvanCloud(id, ipAddressModel.ip_addr);
//                new NETGET().execute(id, ipAddressModel.ip_addr);
//
//            }
//
//            @Override
//            public void onFailure(Call<IpAddressModel> call, Throwable t) {
//                Log.i(TAG, "GetIpAddress failed: " + t.toString());
//
//            }
//        });
//
//
//    }

//    class NETGET extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... p) {
//            return nw.GETDATA("videos/" + p[0] + "?secure_ip=" + p[1]);
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Log.i(TAG, "onPostExecute NetWork2 result: " + result);
//            Gson gson = new Gson();
//            Type listType = new TypeToken<MovieArvanCloudModel>() {
//            }.getType();
//            MAC = gson.fromJson(result.toString(), listType);
//
//        }
//
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MovieActivity.this, MainActivity.class));
        finish();
    }

//    void dialogGrade() {
//        final Dialog dialog = new Dialog(MovieActivity.this, R.style.NewDialog);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_grade);
//        dialog.setCancelable(true);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//    }


}
