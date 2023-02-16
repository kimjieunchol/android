package com.example.main;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class Alphabet2 extends AppCompatActivity {

    ViewPager viewPager = null;
    //private SoundPool write;
    private static MediaPlayer write;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        write = MediaPlayer.create(this, R.raw.write);
        write.start();
        //viewPager
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        fragment adapter = new fragment(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }}