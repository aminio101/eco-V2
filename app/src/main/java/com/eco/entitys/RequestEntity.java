package com.eco.entitys;

import java.util.HashMap;
import java.util.Map;

public class RequestEntity {
    public Map<String, Integer> request = new HashMap<>();
    public Map<String, Double> location  = new HashMap <>();
    public String address;
    public String runDate;
    public String period;

    public void setRequest(Map<String, Integer> value) {
        this.request = value;
    }


    public void setLocation(Map<String, Double> value) {
        this.location = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String value) {
        this.address = value;
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String value) {
        this.runDate = value;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String value) {
        this.period = value;
    }
}
