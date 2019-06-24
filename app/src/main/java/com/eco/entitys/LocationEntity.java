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


    public LocationEntity(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }
    public void LocationEntity() {

    }

    public double getLng () {
        return lng;
    }

    public void setLng (double lng) {
        this.lng = lng;
    }

    public double getLat () {
        return lat;
    }

    public void setLat (double lat) {
        this.lat = lat;
    }
}
