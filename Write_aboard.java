package com.example.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class Write_aboard extends AppCompatActivity {

    private static MediaPlayer apple;

    Integer[] tileType = {
            R.drawable.no_tile, R.drawable.tile, R.drawable.tile_start, R.drawable.tile_apple, R.drawable.wall_tree, R.drawable.wall_trap, R.drawable.wall_light
    };
    Integer[] mapType = {
            R.raw.mapfile1, R.raw.mapfile2, R.raw.mapfile3, R.raw.mapfile4, R.raw.mapfile5
    };

    int mCountWidth = 50; //맵의 가로 갯수
    int mCountHeight = 50; //맵의 세로 갯수

    int startPosition, currentPosition;
    int mapArr[] = null;
    GridLayout dirLayout;
    ImageButton dirButton;
    private static ArrayList dirArrayList;
    int mapCount;
    GridView baseView;
    ImageView mLittleWitch;
    ImageButton goalImageView;
    boolean mGoal = false;
    boolean mReset = false;

    boolean mStop = false;

    int mWidth, mHeight;
    int mIndex = 0;
    int mapIndex;
    private TimerTask mTimerTaskMove, mTimerTaskReset;

    private Timer timerMove, timerReset;

    private final Handler handler = new Handler();

    ImageButton dirTop, dirBottom, dirLeft, dirRight, startBtn, resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_aboard);

        Intent intent = getIntent();

        mapIndex = intent.getExtras().getInt("mapFile");


        //배경음악 가져오기
        apple = MediaPlayer.create(this, R.raw.apple_sound);

        goalImageView = (ImageButton) findViewById(R.id.goalImageView);

        Drawable goalImageView_back = ((ImageButton) findViewById(R.id.goalImageView)).getBackground();
        goalImageView_back.setAlpha(150);

        String map = readTxt();
        mLittleWitch = (ImageView) findViewById(R.id.lwImageView);

        StringTokenizer tokens = new StringTokenizer(map, ",");
        mapCount = tokens.countTokens() - 1;
        mapArr = new int[mapCount];


        int i = 0;
        while (tokens.hasMoreTokens() && i < mapCount + 1) {
            if (i == 0) startPosition = Integer.parseInt(tokens.nextToken());
            else mapArr[i - 1] = Integer.parseInt(tokens.nextToken());
            i++;
        }

        //그리드뷰 설정
        baseView = (GridView) findViewById(R.id.mapGridView);
        MapGridAdapter gAdapter = new MapGridAdapter(this);
        baseView.setAdapter(gAdapter);
        baseView.setNumColumns(50); //가로 갯수

        dirLayout = (GridLayout) findViewById(R.id.dirGridLayout);
        dirLayout.setColumnCount(50);
        dirLayout.setRowCount(2);

        dirArrayList = new ArrayList<Character>();

        dirTop = (ImageButton) findViewById(R.id.topBtn);
        dirTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton dirButton = new ImageButton(Write_aboard.this);
                dirButton.setImageResource(R.drawable.dir_top);
                dirButton.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                dirButton.setBackgroundColor(Color.TRANSPARENT);
                dirLayout.addView(dirButton, dirLayout.getWidth() / 10, dirLayout.getHeight() / 2);
                dirArrayList.add('T');

            }
        });

        dirBottom = (ImageButton) findViewById(R.id.bottomBtn);
        dirBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton dirButton = new ImageButton(Write_aboard.this);
                dirButton.setImageResource(R.drawable.dir_bottom);
                dirButton.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                dirButton.setBackgroundColor(Color.TRANSPARENT);
                dirLayout.addView(dirButton, dirLayout.getWidth() / 10, dirLayout.getHeight() / 2);
                dirArrayList.add('B');

            }
        });

        dirLeft = (ImageButton) findViewById(R.id.leftBtn);
        dirLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton dirButton = new ImageButton(Write_aboard.this);
                dirButton.setImageResource(R.drawable.dir_left);
                dirButton.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                dirButton.setBackgroundColor(Color.TRANSPARENT);
                dirLayout.addView(dirButton, dirLayout.getWidth() / 10, dirLayout.getHeight() / 2);
                dirArrayList.add('L');

            }
        });

        dirRight = (ImageButton) findViewById(R.id.rightBtn);
        dirRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton dirButton = new ImageButton(Write_aboard.this);
                dirButton.setImageResource(R.drawable.dir_right);
                dirButton.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                dirButton.setBackgroundColor(Color.TRANSPARENT);
                dirLayout.addView(dirButton, dirLayout.getWidth() / 10, dirLayout.getHeight() / 2);
                dirArrayList.add('R');
            }
        });

        startBtn = (ImageButton) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dirArrayList.size() > 0) {
                    mGoal = false;
                    currentPosition = startPosition;
                    mIndex = 0;
                    MoveStart();
                    dirTop.setEnabled(false);
                    dirTop.setImageResource(R.drawable.top2);
                    dirBottom.setEnabled(false);
                    dirBottom.setImageResource(R.drawable.bottom2);
                    dirLeft.setEnabled(false);
                    dirLeft.setImageResource(R.drawable.left2);
                    dirRight.setEnabled(false);
                    dirRight.setImageResource(R.drawable.right2);
                    startBtn.setEnabled(false);
                }

            }
        });

        ImageButton resetBtn = (ImageButton) findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetMode();


            }
        });


        ImageButton levelBtn = (ImageButton) findViewById(R.id.levelBtn);
        levelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Write_aboard.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
//골인하면 과일선택하는 화면으로 넘어간다 fruitMain.class
//음성과 함께 배경에 깔린 팝업을 화면 맨 앞으로 띄운다
        goalImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Write_aboard.this, fruitMain_redhood.class);
                startActivity(intent);
                finish();
            }
        });

        goalImageView.bringToFront();


    }


    public void ResetMode() {
        dirArrayList.clear();
        mIndex = 0;
        moveLittleWitch(startPosition);
        dirLayout.removeAllViews();
        if (timerMove != null) timerMove.cancel();
        if (timerReset != null) timerReset.cancel();
        mGoal = false;
        mReset = false;
        goalImageView.setVisibility(View.INVISIBLE);
        dirTop.setEnabled(true);
        dirTop.setImageResource(R.drawable.button_top);
        dirBottom.setEnabled(true);
        dirBottom.setImageResource(R.drawable.button_bottom);
        dirLeft.setEnabled(true);
        dirLeft.setImageResource(R.drawable.button_left);
        dirRight.setEnabled(true);
        dirRight.setImageResource(R.drawable.button_right);
        startBtn.setEnabled(true);

    }

    public void MoveStart() {

        mTimerTaskMove = new TimerTask() {
            int posX = currentPosition % 10;
            int posY = currentPosition / 10;

            @Override
            public void run() {
                if (mIndex < dirArrayList.size()) {
                    Log.v("위치", String.valueOf(mIndex) + " " + dirArrayList.get(mIndex).toString() + " " + String.valueOf(posX) + " " + String.valueOf(posY));
                    if (dirArrayList.get(mIndex).toString().equals("R")) {
                        if (mapArr[posY * 10 + (posX + 1)] == 1 || mapArr[posY * 10 + (posX + 1)] == 2) {
                            posX = posX + 1;
                            currentPosition = (posY) * 10 + (posX);
                            mIndex = mIndex + 1;
                            MoveUpdate();

                        } else if (mapArr[posY * 10 + (posX + 1)] == 6) {
                            posX=posX+1;
                            currentPosition = (posY) * 10 + (posX);
                            MoveUpdate();
                            mStop = true;

                        } else if (mapArr[posY * 10 + (posX + 1)] == 3) {
                            posX = posX + 1;
                            currentPosition = (posY) * 10 + (posX);
                            apple.setLooping(false);
                            apple.start();
                            MoveUpdate();
                            mIndex = mIndex + 1;
                            mGoal = true;
                        } else if (mapArr[posY * 10 + (posX + 1)] == 5) {
                            posX = posX + 1;
                            currentPosition = (posY) * 10 + (posX);
                            MoveUpdate();
                            mIndex = mIndex + 1;
                            mReset = true;
                        } else mIndex = mIndex + 1;


                    } else if (dirArrayList.get(mIndex).toString().equals("T")) {
                        if (posY > 0) {
                            if (mapArr[(posY - 1) * 10 + posX] == 1 || mapArr[(posY - 1) * 10 + posX] == 2) {
                                posY = posY - 1;
                                currentPosition = posY * 10 + posX;
                                MoveUpdate();
                                mIndex = mIndex + 1;
                            } else if (mapArr[(posY - 1) * 10 + posX] == 6) {
                                currentPosition = posY * 10 + posX;
                                posY=posY+1;
                                MoveUpdate();
                                mStop = true;
                            } else if (mapArr[(posY - 1) * 10 + posX] == 3) {
                                posY = posY - 1;
                                currentPosition = posY * 10 + posX;
                                apple.setLooping(false);
                                apple.start();
                                MoveUpdate();
                                mIndex = mIndex + 1;
                                mGoal = true;
                            } else if (mapArr[(posY - 1) * 10 + posX] == 5) {
                                posY = posY - 1;
                                currentPosition = posY * 10 + posX;
                                MoveUpdate();
                                mIndex = mIndex + 1;
                                mReset = true;
                            } else mIndex = mIndex + 1;
                        } else mIndex = mIndex + 1;

                    } else if (dirArrayList.get(mIndex).toString().equals("B")) {
                        if (posY < 5) {
                            if (mapArr[(posY + 1) * 10 + posX] == 1 || mapArr[(posY + 1) * 10 + posX] == 2) {
                                posY = posY + 1;
                                currentPosition = (posY) * 10 + posX;
                                MoveUpdate();
                                mIndex = mIndex + 1;
                            } else if (mapArr[(posY + 1) * 10 + posX] == 6) {
                                currentPosition = (posY) * 10 + posX;
                                posY=posY+1;
                                MoveUpdate();
                                mStop = true;
                            } else if (mapArr[(posY + 1) * 10 + posX] == 3) {
                                posY = posY + 1;
                                currentPosition = (posY) * 10 + posX;
                                MoveUpdate();
                                apple.setLooping(false);
                                apple.start();
                                mIndex = mIndex + 1;
                                mGoal = true;
                            } else if (mapArr[(posY + 1) * 10 + posX] == 5) {
                                posY = posY + 1;
                                currentPosition = (posY) * 10 + posX;
                                MoveUpdate();

                                mIndex = mIndex + 1;
                                mReset = true;
                            } else mIndex = mIndex + 1;
                        } else mIndex = mIndex + 1;


                    } else if (dirArrayList.get(mIndex).toString().equals("L")) {
                        if (mapArr[(posY) * 10 + posX - 1] == 1 || mapArr[(posY) * 10 + posX - 1] == 2) {
                            posX = posX - 1;
                            currentPosition = (posY) * 10 + posX;
                            MoveUpdate();
                            mIndex = mIndex + 1;
                        } else if (mapArr[(posY) * 10 + posX - 1] == 6) {
                            posX=posX+1;
                            currentPosition = (posY) * 10 + posX;
                            MoveUpdate();
                            mStop = true;
                        } else if (mapArr[(posY) * 10 + posX - 1] == 3) {
                            posX = posX - 1;
                            currentPosition = (posY) * 10 + posX;
                            apple.setLooping(false);
                            apple.start();
                            MoveUpdate();
                            mIndex = mIndex + 1;
                            mGoal = true;
                        } else if (mapArr[(posY) * 10 + posX - 1] == 5) {
                            posX = posX - 1;
                            currentPosition = (posY) * 10 + posX;
                            MoveUpdate();
                            mIndex = mIndex + 1;
                            mReset = true;
                        } else mIndex = mIndex + 1;


                    }

                }
            }


        };
        timerMove = new Timer();

        timerMove.schedule(mTimerTaskMove, 0, 500);

    }

    protected void MoveUpdate() {
        int posX = currentPosition % 10;
        int posY = currentPosition / 10;

        Runnable updater = new Runnable() {

            public void run() {
                moveLittleWitch(currentPosition);
                if (mGoal) goalImageView.setVisibility(View.VISIBLE);
                if (mReset) ResetStart();
                if (mStop) mIndex=dirArrayList.size();
            }

        };
        handler.post(updater);


    }

    public void ResetStart() {

        mTimerTaskReset = new TimerTask() {
            @Override
            public void run() {
                ResetUpdate();
            }
        };
        timerReset = new Timer();

        timerReset.schedule(mTimerTaskReset, 500);
    }

    protected void ResetUpdate() {

        Runnable updater = new Runnable() {

            public void run() {
                ResetMode();

            }

        };

        handler.post(updater);


    }

    private String readTxt() {

        String data = null;
        InputStream inputStream = getResources().openRawResource(mapType[mapIndex - 1]);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }

            data = new String(byteArrayOutputStream.toByteArray(), "MS949");
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        FrameLayout layoutMainView = (FrameLayout) this.findViewById(R.id.mFrameLayout);

        mWidth = layoutMainView.getMeasuredWidth() / 10;
        mHeight = layoutMainView.getMeasuredHeight() / 6;

        printLittleWitch();
    }

    public class MapGridAdapter extends BaseAdapter {
        Context context;

        public MapGridAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return mapCount;
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {

            return position - 1;
        }

        public View getView(int position, View convertView, ViewGroup arg2) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(baseView.getWidth() / 10, baseView.getHeight() / 6));
                imageView.setAdjustViewBounds(false);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(0, 0, 0, 0);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(tileType[mapArr[position]]);


            return imageView;
        }
    }

    //꼬마마녀 시작위치 출력하기
    public void printLittleWitch() {

        int posX = startPosition % 5;      //startPosition % 10;
        int posY = startPosition / 5 - 1;
        //꼬마 크기
        mLittleWitch.setScaleX(mWidth * 1.0f / mLittleWitch.getWidth() * 1.0f);
        mLittleWitch.setScaleY(mHeight * 1.0f / mLittleWitch.getHeight() * 1.0f);

        mLittleWitch.setX(posX * mWidth);
        mLittleWitch.setY((posY + 1) * mHeight);
        if ((mWidth * 1.0f / mLittleWitch.getWidth() * 1.0f) <= 0.6f) {
            mLittleWitch.setX(posX * mWidth - 60);
            mLittleWitch.setY(posY * mHeight + 60);
        }


        mLittleWitch.setVisibility(View.VISIBLE);
        Log.i("pos1", String.valueOf(mWidth) + String.valueOf(mHeight));
        Log.i("pos1", String.valueOf(mLittleWitch.getHeight()) + String.valueOf(mLittleWitch.getHeight()));
    }

    public void moveLittleWitch(int pos) {

        int posX = pos % 10;
        int posY = pos / 10 - 1;
        //꼬마 크기
        mLittleWitch.setScaleX(mWidth * 1.0f / mLittleWitch.getWidth() * 1.0f);
        mLittleWitch.setScaleY(mHeight * 1.0f / mLittleWitch.getHeight() * 1.0f);

        //  mLittleWitch.setScaleX(0.3f);
        //  mLittleWitch.setScaleY(0.3f);
        mLittleWitch.setX(posX * mWidth);
        mLittleWitch.setY((posY + 1) * mHeight);

        if ((mWidth * 1.0f / mLittleWitch.getWidth() * 1.0f) <= 0.6f) {
            mLittleWitch.setX(posX * mWidth - 60);
            mLittleWitch.setY(posY * mHeight + 60);
        }
        mLittleWitch.setVisibility(View.VISIBLE);

    }
}
