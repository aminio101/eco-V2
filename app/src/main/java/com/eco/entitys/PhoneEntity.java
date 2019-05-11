package com.eco.entitys;

public class PhoneEntity {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    String username;

    public PhoneEntity(String username) {
        this.username = username;
    }
}
