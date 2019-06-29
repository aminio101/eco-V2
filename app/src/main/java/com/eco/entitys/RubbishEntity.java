package com.eco.entitys;

public class RubbishEntity {
    public String type;
    public String picture;
    public int id;
    public int number;
    public String getType() {
        return type == null ? "" : type;
    }
}
