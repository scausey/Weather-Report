package com.squidge.samanthacausey.weatherwithbobross.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by samanthacausey on 5/10/16.
 */
public class Clouds {

    @SerializedName("all")
    @Expose
    private Integer all;

    /**
     * @return The all
     */
    public Integer getAll() {
        return all;
    }

    /**
     * @param all The all
     */
    public void setAll(Integer all) {
        this.all = all;
    }

}
