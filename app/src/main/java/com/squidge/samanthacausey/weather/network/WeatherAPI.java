package com.squidge.samanthacausey.weatherwithbobross.network;

import com.squidge.samanthacausey.weatherwithbobross.data.CityResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by samanthacausey on 5/10/16.
 */
public interface WeatherAPI {

    @GET("/data/2.5/weather?q=Budapest")
    Call<CityResult> getCurrentWeather(@Query("q") String city,
                                       @Query("units") String units,
                                       @Query("appid") String appid
    );

}
