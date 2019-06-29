package com.eco;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.eco.entitys.UserEntity;
import com.google.gson.Gson;

public class PrefManager {
    private static final String PREF_NAME = "ECO";
    private static final String TOKEN = "token";
    private static final String FIRSTTOKEN = "firstToken";
    private static final String USER = "user";
    static Application application;
    private static PrefManager instance;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    Gson gson;

    public PrefManager(Application application) {
        PrefManager.application = application;
        pref = application.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        gson = new Gson();
    }

    public static PrefManager getInstance() {
        if (instance == null)
            instance = new PrefManager(application);

        return instance;
    }


    public void setToken(String token) {
        editor.putString(TOKEN, token);
        editor.commit();
    }
      public void setFirstToken(String token) {
        editor.putString(FIRSTTOKEN, token);
        editor.commit();
    }
    public String getFirstToken() {
        String token = pref.getString(FIRSTTOKEN, null);
        if (null != token) Log.i("Amirhosen FirstToken", token);
        return token;
    }
    public void setUser(UserEntity userEntity) {
        editor.putString(USER, gson.toJson(userEntity));
        editor.commit();
    }

    public String getToken() {
        String token = pref.getString(TOKEN, null);
        if (null != token) Log.i("Amirhosen Token", token);
        return token;
    }


    public UserEntity getUser() {
      String json = null; json = pref.getString(USER, null);
        UserEntity userEntity =  gson.fromJson(json,UserEntity.class);
        return userEntity;
    }
}
