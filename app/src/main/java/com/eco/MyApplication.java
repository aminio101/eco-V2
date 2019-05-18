package com.eco;

import android.app.Application;
import android.content.Context;

import com.eco.rest.MethodApi;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MethodApi.init(this);
        PrefManager prefManager=new PrefManager(this);
    }
    public static Context getContext (){
       return getContext();
    }
}
