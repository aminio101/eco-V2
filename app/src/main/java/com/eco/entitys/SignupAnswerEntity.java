package com.eco.entitys;

public class SignupAnswerEntity {
    public int id;
    public String username;
    public String name;
    public String family;
    public int roleId;
    public String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String value) {
        this.family = value;
    }




    public String getToken() {
        return token;
    }

    public void setToken(String value) {
        this.token = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

