package com.example.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class fragment extends FragmentStatePagerAdapter{

    Fragment[] fragments = new Fragment[4];
    public fragment(FragmentManager fm) {
        super(fm);
        fragments[0] = new view1();
        fragments[1] = new view2();
        fragments[2] = new view3();
        fragments[3] = new view4();
    }

    public Fragment getItem(int arg0) {
        return fragments[arg0];
    }

    public int getCount() {
        return fragments.length;
    }

}
