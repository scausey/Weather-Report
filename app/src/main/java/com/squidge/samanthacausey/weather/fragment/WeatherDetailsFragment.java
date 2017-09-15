package com.squidge.samanthacausey.weatherwithbobross.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class WeatherDetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_weather_details, null);
        final String apiKey = "f84a61c257d0eac1857556779eb5797b";

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://api.openweathermap.org").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        final WeatherAPI weatherAPI = retrofit.create(WeatherAPI.class);

        Bundle extra = getActivity().getIntent().getExtras();
        final String city = (extra.getString("KEY_CITY_NAME"));

        final TextView tvCityName = (TextView) rootView.findViewById(R.id.tvCityName);
        final TextView tvCountryName = (TextView) rootView.findViewById(R.id.tvCountryName);
        final TextView tvTempMax = (TextView) rootView.findViewById(R.id.tvTempMax);
        final TextView tvTempMin = (TextView) rootView.findViewById(R.id.tvTempMin);
        final TextView tvHumidity = (TextView) rootView.findViewById(R.id.tvHumidity);
        final TextView tvRain = (TextView) rootView.findViewById(R.id.tvRain);
        final TextView tvWindSpeed = (TextView) rootView.findViewById(R.id.tvWindSpeed);
        final TextView tvClouds = (TextView) rootView.findViewById(R.id.tvClouds);

        Call<CityResult> cityQuery = weatherAPI.getCurrentWeather(
                city,
                "imperial",
                apiKey);
        cityQuery.enqueue(new Callback<CityResult>() {
            @Override
            public void onResponse(Call<CityResult> call, Response<CityResult> response) {
                tvCityName.setText("City name: " + response.body().getName());
                tvCountryName.setText("Country name: " + response.body().getSys().getCountry());
                tvTempMax.setText("Max temperature: " + response.body().getMain().getTempMax());
                tvTempMin.setText("Min temperature: " + response.body().getMain().getTempMin());
                tvHumidity.setText("Humidity: " + response.body().getMain().getHumidity());
                tvRain.setText("Rain: " + response.body().getRain());
                tvWindSpeed.setText("Wind speed: " + response.body().getWind().getSpeed());
                tvClouds.setText("Clouds: " + response.body().getClouds().getAll());
            }

            @Override
            public void onFailure(Call<CityResult> call, Throwable t) {
                tvTempMax.setText("ERROR: " + t.getMessage());
            }
        });

        return rootView;
    }
}
