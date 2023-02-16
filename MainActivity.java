package com.example.main;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int get;
    ImageView write_bt;
    ImageView coding_bt;
    //ImageView 찾기게임;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent(this, LoadingActivity.class);
        //startActivity(intent);
        //finish();

        ImageView write_bt = (ImageView) findViewById(R.id.write_bt);
        ImageView coding_bt = (ImageView) findViewById(R.id.game_bt);
        //ImageView coding_bt = (ImageView) findViewById(R.id.game_bt); 찾기게임 버튼호출

        write_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Alphabet1.class);
                startActivity(intent);
            }
        });

        coding_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Alphabet1.class);
                startActivity(intent);
            }
        });

        /*coding_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Alphabet1.class);
                startActivity(intent);
            }
        });

        찾기게임 버튼id 입력, intent 이동 class명 변경
        */


    }}
