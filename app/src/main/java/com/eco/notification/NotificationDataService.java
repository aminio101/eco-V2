package com.eco.notification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint("Registered")
public class NotificationDataService extends Service {

    private Intent mIntent;

    public void onMessageReceived(JSONObject data) {
        Log.d("Notif:From", ": " + data.toString());
    }

    @Override
    public void onCreate () {
        super.onCreate();
        int NOTIFICATION_ID = (int) (System.currentTimeMillis()%10000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(NOTIFICATION_ID, new Notification.Builder(this).build());
        }
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {

        mIntent = intent;

        ListenerAsyncTask listenerAsyncTask = new ListenerAsyncTask();
        listenerAsyncTask.execute();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    public class ListenerAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (mIntent.hasExtra("data")) {
                try {
                    final JSONObject data = new JSONObject(mIntent.getStringExtra("data"));
                    onMessageReceived(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
