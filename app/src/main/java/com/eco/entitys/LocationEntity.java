package com.eco.entitys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationEntity {
    @Expose
    @SerializedName("lng")
    private double lng;
    @Expose
    @SerializedName("lat")
    private double lat;




    public double getLng () {
        return lng;
    }

    public void setLng (double lng) {
        this.lng = lng;
    }
    public LocationEntity setFirstLng(double lng) {
        this.lng = lng;
        return this;
    }

    public double getLat () {
        return lat;
    }

    public void setLat (double lat) {
        this.lat = lat;
    }
    public LocationEntity setFirstLat (double lat) {
        this.lat = lat;
        return this;
    }

}
