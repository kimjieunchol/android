package com.example.main;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class LoadingActivity extends Activity {

    private static MediaPlayer loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        loading = MediaPlayer.create(this, R.raw.loading_bg);

        //VideoView loadingV = (ImageView)findViewById(R.id.lion);
        //GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(loadingGif);
        //Glide.with(this).load(R.raw.loading).into(loadingV);
        loading.start();
        startLoading();

        Uri uri = Uri.parse("android.resource://" +getPackageName()+"/"+ R.raw.loading);
        VideoView video = (VideoView)findViewById(R.id.videoView);
        video.setVideoURI(uri);
        video.start();
    }


        private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
                loading.stop();
            }
        }, 10000);
    }
}