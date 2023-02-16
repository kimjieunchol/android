package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class view2 extends Fragment {
    ImageView select_gh;
    ImageView select_ij;
    ImageView select_kl;
    ImageView select_mn;
    ImageView home2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view_page2 = inflater.inflate(R.layout.activity_select2, null);
        return view_page2;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        select_gh = (ImageView) getActivity().findViewById(R.id.select_gh);
        select_ij = (ImageView) getActivity().findViewById(R.id.select_ij);
        select_kl = (ImageView) getActivity().findViewById(R.id.select_kl);
        select_mn = (ImageView) getActivity().findViewById(R.id.select_mn);
        home2 = (ImageView)getActivity().findViewById(R.id.home2);



        select_gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HintActivity.class);
                startActivity(intent);
            }
        });

        select_ij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        select_kl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        select_mn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
