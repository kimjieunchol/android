package com.example.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

public class catch1 extends AppCompatActivity {

    int get;
    ImageView home;
    ImageView alpha1;
    ImageView alpha2;
    VideoView catch_ani;
    boolean mCatch = false;
    //popup_a pop_a;   //popup_a 클래스 불러옴 pop_a로 변수설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch1);
        ImageView i = (ImageView) findViewById(R.id.home1);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(catch1.this,MainActivity.class);
                startActivity(intent);
            }
        });

        /*DisplayMetrics  dm = getApplicationContext().getResources().getDisplayMetrics(); // 디바이스 화면크기 구하기위함
        int width = dm.widthPixels; //전체가로
        int height = dm.heightPixels; //전체세로

        pop_a = new popup_a(this);
        WindowManager.LayoutParams wm = pop_a.getWindow().getAttributes(); //다이얼 로그 창 가로세로 설정
        wm.copyFrom(pop_a.getWindow().getAttributes()); //여기서 설정한 값 그대로 다이얼로그에 넣겠다
        wm.width = width/2; //화면 가로 전체 절반
        wm.height = height/2; //화면 세로 전체 절반
*/
        ImageView home = (ImageView) findViewById(R.id.home1);
        ImageView alpha1 = (ImageView) findViewById(R.id.alpha1);
        ImageView alpha2 = (ImageView) findViewById(R.id.alpha2);
        //catch_ani = (VideoView) findViewById(R.id.catch_ani);
        //Drawable goalImageView_back = ((ImageButton) findViewById(R.id.videoView)).getBackground();
        //goalImageView_back.setAlpha(150);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pop_apple);
        final VideoView catch_ani = (VideoView) findViewById(R.id.catch_ani);
        catch_ani.setVideoURI(uri);

        catch_ani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(catch1.this, Alphabet1.class);
                startActivity(intent);
                finish();
            }
        });
        //catch_ani.bringToFront();
        //catch_ani.start();

       alpha1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               get = 1;
               //Intent intent = new Intent(MainActivity.this, fruitMain.class);
               //startActivity(intent);

               mCatch = true;
               //animation();
               //pop_a.show();
               catch_ani.bringToFront();
               catch_ani.setVisibility(View.VISIBLE);
               catch_ani.start();
            }
       });


       alpha2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               get = 2;
               Toast.makeText(catch1.this, "다시 선택해 보세요", Toast.LENGTH_SHORT).show();
           }
       });

       home.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(catch1.this, MainActivity.class);
               startActivity(intent);
           }
       });


       /* Runnable updater = new Runnable() {

            public void run() {
                moveLittleWitch(currentPosition);
                if (mCatch) goalImageView.setVisibility(View.VISIBLE);
            }

        };
        //handler.post(updater);
    }*/


        /*if(get==1){
            catch_ani.bringToFront();
            catch_ani.start();
        }*/

   }
}
