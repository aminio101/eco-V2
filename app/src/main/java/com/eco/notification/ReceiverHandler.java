package com.eco.notification;

import android.util.Log;

import org.json.JSONObject;


public class ReceiverHandler extends NotificationDataService {


    @Override
    public void onMessageReceived (JSONObject data) {
        try {
            Log.d("Notif+ : From", ": " + data.toString());


        } catch (Exception e) {
            Log.d("onMessageReceived", ": " +e);

        }

    }
}
