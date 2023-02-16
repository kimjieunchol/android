package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class fruitMain_redhood extends AppCompatActivity {

    ImageView button;
    ImageView apple;
    ImageView banana;
    ImageView pear;
    ImageView peach;
    ImageView orange;
    int get = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        button = (ImageView) findViewById(R.id.start);
        apple = (ImageView) findViewById(R.id.apple_btn);
        banana = (ImageView) findViewById(R.id.banana_btn);
        pear = (ImageView) findViewById(R.id.pear_btn);
        peach = (ImageView) findViewById(R.id.peach_btn);
        orange = (ImageView) findViewById(R.id.orange_btn);


        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get =5;
            }
        });

        banana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get =6;
            }
        });

        peach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get =7;
            }
        });

        pear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get =8;
            }
        });

        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get =9;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (get == 5) {
                    Intent intent = new Intent(fruitMain_redhood.this, Map_apple_redhood.class);
                    intent.putExtra("mapFile", 1);
                    startActivity(intent);
                    finish();
                }else if(get==6){
                    Intent intent = new Intent(fruitMain_redhood.this, Map_banana_redhood.class);
                    intent.putExtra("mapFile", 2);
                    startActivity(intent);
                    finish();
                }else if(get==7){
                    Intent intent = new Intent(fruitMain_redhood.this, Map_peach_redhood.class);
                    intent.putExtra("mapFile", 3);
                    startActivity(intent);
                    finish();
                }else if(get==8){
                    Intent intent = new Intent(fruitMain_redhood.this, Map_pear_redhood.class);
                    intent.putExtra("mapFile", 4);
                    startActivity(intent);
                    finish();
                }else if(get==9){
                    Intent intent = new Intent(fruitMain_redhood.this, Map_orange_redhood.class);
                    intent.putExtra("mapFile", 5);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(fruitMain_redhood.this, "과일을 선택해주세요", Toast.LENGTH_SHORT).show();
                }}
        });
    }
}