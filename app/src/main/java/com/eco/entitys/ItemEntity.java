package com.eco.entitys;

public class ItemEntity {
    public String name;
    public String number;
    public int id;

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ItemEntity(){

    }
    public ItemEntity(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
