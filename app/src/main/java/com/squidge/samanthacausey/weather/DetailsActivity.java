package com.squidge.samanthacausey.weatherwithbobross;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squidge.samanthacausey.weatherwithbobross.adapter.MyPagerAdapter;

/**
 * Created by samanthacausey on 5/9/16.
 */
public class DetailsActivity extends AppCompatActivity {
    public static final String KEY_CITY_NAME = "KEY_CITY_NAME";
    private String cityString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        MyPagerAdapter adapter = new MyPagerAdapter(
                getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(adapter);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                cityString = null;
            } else {
                cityString = extras.getString("CITY_STRING");
            }
        } else {
            cityString = (String) savedInstanceState.getSerializable("CITY_STRING");
        }
    }
}
