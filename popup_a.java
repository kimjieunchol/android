package com.example.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class popup_a extends Dialog {

    VideoView pop_a;

   public popup_a(Context context) {
       super(context);
       requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀바삭제
       getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //배경투명
       setContentView(R.layout.activity_popup_a);
       VideoView pop_a = (VideoView) findViewById(R.id.videoView);
       pop_a.start();

       //dismiss(); //다이얼로그 닫기

   }

}
