package com.squidge.samanthacausey.weatherwithbobross.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squidge.samanthacausey.weatherwithbobross.R;
import com.squidge.samanthacausey.weatherwithbobross.data.CityResult;
import com.squidge.samanthacausey.weatherwithbobross.network.WeatherAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by samanthacausey on 5/9/16.
 */
public class WeatherIconFragment extends Fragment {

    private TextView tvWeatherDetailsResult;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_weather_icon, null);
        String KEY_CITY_NAME = "KEY_CITY_NAME";
        String apiKey = "f84a61c257d0eac1857556779eb5797b";

        ImageView imageView = (ImageView) rootView.findViewById(R.id.my_image_view);
        Glide.with(this).load("http://openweathermap.org/img/w/10d.png").into(imageView);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://api.openweathermap.org").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        final WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

        Bundle extra = getActivity().getIntent().getExtras();
        final String city = (extra.getString("KEY_CITY_NAME"));

        TextView tvCityName = (TextView) rootView.findViewById(R.id.tvCityName);
        tvCityName.setText(city);

        final TextView tvCurrentTemp = (TextView) rootView.findViewById(R.id.tvTempMax);
        final TextView tvDescription = (TextView) rootView.findViewById(R.id.tvDescription);

        Call<CityResult> cityQuery = weatherAPI.getCurrentWeather(
                city,
                "imperial",
                apiKey);
        cityQuery.enqueue(new Callback<CityResult>() {
            @Override
            public void onResponse(Call<CityResult> call, Response<CityResult> response) {
                //Get current temperature.
                tvCurrentTemp.setText("Current temperature is: " + response.body().getMain().getTemp());
                //Get description.
                tvDescription.setText("" + response.body().getWeather());
            }

            @Override
            public void onFailure(Call<CityResult> call, Throwable t) {
                tvCurrentTemp.setText("ERROR: " + t.getMessage());
            }
        });

        return rootView;
    }
}
