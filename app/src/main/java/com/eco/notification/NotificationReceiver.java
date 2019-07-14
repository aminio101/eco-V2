package com.eco.notification;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.collection.ArrayMap;
import androidx.core.app.NotificationCompat;

import com.eco.PV;
import com.eco.R;
import com.eco.activityes.MainActivity;
import com.eco.entitys.AcceptDriverEntity;
import com.eco.entitys.ItemEntity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.Map;


public class NotificationReceiver extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo_green))
                .setSmallIcon(R.drawable.logo_green)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());


        try {
            Log.d("Notif nMessageRecd", ": " + remoteMessage.getNotification().getBody());
            String str2 = "پذیرش درخواست";

            String jsonObject = remoteMessage.getData().get("data");
            String test = remoteMessage.getData().get("title");


            if (test.toLowerCase().contains(str2.toLowerCase())) {
                AcceptDriverEntity acceptDriverEntity = new AcceptDriverEntity();
                acceptDriverEntity.title = remoteMessage.getData().get("title");
                acceptDriverEntity.body = remoteMessage.getData().get("body");
                acceptDriverEntity.time = remoteMessage.getData().get("data");
                JSONObject jsonObject1 = new JSONObject(acceptDriverEntity.time);
                acceptDriverEntity.thumbnail = jsonObject1.getString("thumbnail");
                acceptDriverEntity.carName = jsonObject1.getString("carName");
                acceptDriverEntity.time = jsonObject1.getString("time");
                acceptDriverEntity.name = jsonObject1.getString("name");
                acceptDriverEntity.family = jsonObject1.getString("family");
//                acceptDriverEntity.thumbnail = remoteMessage.getData().get("thumbnail");


                if (PV.activity instanceof MainActivity) {
                    ((MainActivity) PV.activity).showDriverDialog(acceptDriverEntity);
                }
            } else {
                String number = jsonObject.replaceAll("[^0-9]", "");
                if (PV.activity instanceof MainActivity) {
                    ((MainActivity) PV.activity).loadCommentFragment(number);
                }
            }

        } catch (Exception e) {
            Log.d("onMessageReceived", "Exception: " + e);
        }


    }
}
