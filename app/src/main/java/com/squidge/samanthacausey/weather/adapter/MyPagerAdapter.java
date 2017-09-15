package com.squidge.samanthacausey.weatherwithbobross.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.squidge.samanthacausey.weatherwithbobross.fragment.WeatherDetailsFragment;
import com.squidge.samanthacausey.weatherwithbobross.fragment.WeatherIconFragment;

public class MyPagerAdapter  extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return new WeatherIconFragment();
            case 1:
                return new WeatherDetailsFragment();
            default:
                return new WeatherIconFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Weather Summary";
            case 1:
                return "Weather Details";
            default:
                return "unknown";
        }
    }
}