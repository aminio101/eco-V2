package com.eco;

import android.app.Application;
import android.content.Context;

import com.eco.rest.MethodApi;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MethodApi.init(this);
        PrefManager prefManager=new PrefManager(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANSansMobile1.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
    public static Context getContext (){
       return getContext();
    }
}
