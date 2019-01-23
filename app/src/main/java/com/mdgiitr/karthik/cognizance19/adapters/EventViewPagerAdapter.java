package com.mdgiitr.karthik.cognizance19.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;

public class EventViewPagerAdapter extends FragmentPagerAdapter {
    private HashMap<Integer, Fragment> map;

    public EventViewPagerAdapter(FragmentManager fm, HashMap<Integer, Fragment> map) {
        super(fm);
        this.map = map;
    }

    @Override
    public Fragment getItem(int i) {
        return map.get(i);
    }

    @Override
    public int getCount() {
        return map.size();
    }
}
