package com.xin.xiaoxinzone.UI.Main.HeadNews.Adapter;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 2017/11/13.
 */

public class ExamplePagerAdapter extends FragmentPagerAdapter {

    public List<Fragment> fragments = new ArrayList<>();

    public ExamplePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ExamplePagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
