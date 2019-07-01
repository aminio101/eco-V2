package com.eco.entitys;

import java.util.HashMap;
import java.util.Map;

public class DriverEntity {
    public int id;
    public int score;
    public int driverId;
    public String name;
    public String family;
    public String thumbpic;
    public Map<String,Integer> rubish = new HashMap<>();
}
