package com.squidge.samanthacausey.weatherwithbobross.data;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by samanthacausey on 5/3/16.
 */
public class City extends SugarRecord implements Serializable {
    private String cityName;

    //Empty constructor for SugarDB.
    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
