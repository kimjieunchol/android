package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Alphabet1 extends AppCompatActivity {

    ImageView write;
    ImageView coding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet1);
        ImageView i = (ImageView)findViewById(R.id.home1);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Alphabet1.this,MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView write = (ImageView) findViewById(R.id.write);
        ImageView coding = (ImageView) findViewById(R.id.coding);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Alphabet1.this, Alphabet2.class);
                startActivity(intent);
            }
        });
        coding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Alphabet1.this,catch1.class);
                startActivity(intent);
            }
        });

    }}
