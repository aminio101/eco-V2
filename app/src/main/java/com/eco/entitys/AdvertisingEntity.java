package com.eco.entitys;

public class AdvertisingEntity {
    public int id;
    public String description;
    public String path;
    public String getPath() {
        return path == null ? "" : path;
    }
}
