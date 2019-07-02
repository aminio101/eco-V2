package com.eco.notification;

import android.util.Log;

import org.json.JSONObject;


public class OpenedHandler extends NotificationDataService {

    @Override
    public void onMessageReceived (JSONObject data) {
        Log.d("Notif : From", "OpenedHandler: " + data.toString());


    }
}
