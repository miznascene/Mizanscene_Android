package com.example.mizansen.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mizansen.R;
import com.khizar1556.mkvideoplayer.MKPlayer;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class ShowShortMovieActivity extends Activity {


    MKPlayer player;
    String TAG = "ShowmovieActivity", Link = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showshortmovie);


        initView();

        player.onComplete(new Runnable() {
            @Override
            public void run() {
                //callback when video is finish
                Toast.makeText(getApplicationContext(), "video play completed", Toast.LENGTH_SHORT).show();
            }
        }).onInfo(new MKPlayer.OnInfoListener() {
            @Override
            public void onInfo(int what, int extra) {
                switch (what) {
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_START:
                        Log.i(TAG, "MEDIA_INFO_BUFFERING_START");
                        break;
                    case IMediaPlayer.MEDIA_INFO_BUFFERING_END:
                        Log.i(TAG, "MEDIA_INFO_BUFFERING_END");
                        break;
                    case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH:
                        Log.i(TAG, "MEDIA_INFO_NETWORK_BANDWIDTH");
                        break;
                    case IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                        Log.i(TAG, "MEDIA_INFO_VIDEO_RENDERING_START");
                        break;
                }
            }
        }).onError(new MKPlayer.OnErrorListener() {
            @Override
            public void onError(int what, int extra) {
                Toast.makeText(getApplicationContext(), "video play error", Toast.LENGTH_SHORT).show();
            }
        });


        player.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
            @Override
            public void onNextClick() {

            }

            @Override
            public void onPreviousClick() {

            }
        });

    }


    void initView() {


        player = new MKPlayer(this);


        Bundle bundle = getIntent().getExtras();
        Link = bundle.getString("LinkShortMovie");

        player.play(Link);

    }

    @Override
    public void onBackPressed() {
        player.stop();
        finish();
        super.onBackPressed();

    }
}
