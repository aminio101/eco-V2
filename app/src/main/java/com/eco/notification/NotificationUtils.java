package com.eco.notification;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.text.Html;

import com.eco.BuildConfig;
import com.eco.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;



public class NotificationUtils {

    private static final String serviceNameOpened = "co.silverAge.OPENED";
    private static final String serviceNameReceiver = "co.silverAge.RECEIVER";

    public static void showMessage(final Context context, final Map<String, String> data) {

        final int id = Integer.parseInt(data.get("id"));
        final String title = data.get("title");
        final String message = data.get("message");
        final String large_icon = data.get("largeIcon");
        Uri alarmSound;
        final String picture = data.get("picture");
        final int sound = Integer.parseInt(data.get("sound"));
        final String lightColor = data.get("ledColor");
        final int vibrate = Integer.parseInt(data.get("vibrate"));
        final String notifyType = data.get("notifyType");
        boolean isData = false;
        boolean autoRun = false;
        if (data.get("autoRun") != null)
            autoRun = Boolean.parseBoolean(data.get("autoRun"));
        JSONObject extraData = null;
        String type = "";
        String url = "";
        PendingIntent pendingIntent = null;

        try {
            if (data.get("extraData") != null) {
                extraData = new JSONObject(data.get("extraData"));
//
//                MainActivity mainActivity =new MainActivity();
//
//                mainActivity.startComment();
//


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (data.get("action") != null) {
            try {
                final JSONObject action = new JSONObject(data.get("action"));
                type = action.getString("type");
                url = action.getString("url");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        switch (sound) {
            case 1: {
                alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://" + context.getPackageName() + "/raw/sound_one");
                break;
            }
            case 2: {
                alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://" + context.getPackageName() + "/raw/sound_two");
                break;
            }
            default: {
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                break;
            }
        }

        switch (notifyType) {

            case "data": {
                isData = true;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    context.startForegroundService(dataReceiver(context, extraData));

                } else {
                    context.startService(dataReceiver(context, extraData));
                }

                break;
            }
            case "notifyData": {
                if (autoRun){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        context.startForegroundService(dataReceiver(context, extraData));
                    } else {
                        context.startService(dataReceiver(context, extraData));
                    }
                }
                else {
                    if (isAppIsInBackground(context))
                        pendingIntent = pendingBroadCast(context, extraData);
                    else{

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                            context.startForegroundService(dataReceiver(context, extraData));
                        } else {
                            context.startService(dataReceiver(context, extraData));
                        }
                    }
                }
                break;
            }
            case "notify": {
                pendingIntent = pendingActivity(context, type, url);
                break;
            }
            default: {
                pendingIntent = pendingActivity(context, type, url);
                break;
            }
        }

        if (!isData) {

            if (picture != null)
                showBig(id
                        , context
                        , title, message
                        , large_icon
                        , picture
                        , alarmSound
                        , lightColor
                        , vibrate
                        , pendingIntent);

            else showSmall(id
                    , context
                    , title
                    , message
                    , large_icon
                    , alarmSound
                    , lightColor
                    , vibrate
                    , pendingIntent);

        }
    }

    private static Intent dataOpened(Context context, JSONObject extraData) {

        final Intent intent = new Intent(serviceNameOpened);
        intent.setPackage(context.getPackageName());
        if (extraData != null)
            intent.putExtra("data", extraData.toString());
        else intent.putExtra("data", "");

        return intent;
    }

    private static Intent dataReceiver(Context context, JSONObject extraData) {

        final Intent intent = new Intent(serviceNameReceiver);
        intent.setPackage(context.getPackageName());
        if (extraData != null)
            intent.putExtra("data", extraData.toString());
        else intent.putExtra("data", "");

        return intent;
    }

    private static PendingIntent pendingBroadCast(Context context, JSONObject extraData) {

        return PendingIntent.getService(context, 0, dataOpened(context, extraData),
                PendingIntent.FLAG_ONE_SHOT);
    }

    private static PendingIntent pendingActivity(Context context, String type, String url) {

        Intent intent;
        switch (type) {
            case "A": {

                try {
                    Class CUSTOM_ACTION = Class.forName(context.getPackageName() + "." + url);
                    intent = new Intent(context, CUSTOM_ACTION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                } catch (ClassNotFoundException e) {

                    intent = context.getPackageManager()
                            .getLaunchIntentForPackage(BuildConfig.APPLICATION_ID);
                    assert intent != null;
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
                break;
            }
            default: {
                intent = context.getPackageManager()
                        .getLaunchIntentForPackage(BuildConfig.APPLICATION_ID);
                assert intent != null;
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                break;
            }
        }

        return PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
    }

    private static void showSmall(final int id
            , final Context context
            , final String title
            , final String message
            , final String large_icon
            , final Uri alarmSound
            , final String lightColor
            , final int vibrate
            , final PendingIntent pendingIntent) {

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(Html.fromHtml(message).toString());

        String channelId = context.getResources().getString(R.string.app_name);
        final NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(context.getApplicationInfo().icon)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentIntent(pendingIntent)
                        .setSound(alarmSound)
                        .setStyle(inboxStyle)
                        .setLights(Color.parseColor(lightColor), 3000, 3000)
                        .setLargeIcon(getBitmapFromURL(large_icon))
                        .setContentText(Html.fromHtml(message).toString());

        if (vibrate != 0)
            notificationBuilder.setVibrate(new long[]{1000, 1000});

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(channelId,
                    "Notifications",
                    NotificationManager.IMPORTANCE_HIGH);

            channel.enableLights(true);
            channel.setLightColor(Color.RED);

            if (vibrate != 0) {
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{1000, 1000});
            }

            AudioAttributes aa = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();

            channel.setSound(alarmSound, aa);

            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        assert notificationManager != null;
        notificationManager.notify(id, notificationBuilder.build());
    }

    private static void showBig(final int id
            , final Context context
            , final String title
            , final String message
            , final String large_icon
            , final String picture
            , final Uri alarmSound
            , final String lightColor
            , final int vibrate
            , final PendingIntent pendingIntent) {

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle(title);
        bigPictureStyle.setSummaryText(Html.fromHtml(message).toString());
        bigPictureStyle.bigPicture(getBitmapFromURL(picture));

        String channelId = context.getResources().getString(R.string.app_name);
        final NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(context.getApplicationInfo().icon)
                        .setLargeIcon(getBitmapFromURL(large_icon))
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(Html.fromHtml(message).toString())
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setLights(Color.parseColor(lightColor), 3000, 3000)
                        .setSound(alarmSound)
                        .setStyle(bigPictureStyle);

        if (vibrate != 0)
            notificationBuilder.setVibrate(new long[]{1000, 1000});

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Notifications",
                    NotificationManager.IMPORTANCE_HIGH);

            channel.enableLights(true);
            channel.setLightColor(Color.RED);

            if (vibrate != 0) {
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{1000, 1000});
            }

            AudioAttributes aa = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();

            channel.setSound(alarmSound, aa);

            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        assert notificationManager != null;
        notificationManager.notify(id, notificationBuilder.build());
    }

    private static Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void clearNotifications(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.cancelAll();
    }

    private static void playNotificationSound(Context context) {
        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + context.getPackageName() + "/raw/sound_two");
            Ringtone r = RingtoneManager.getRingtone(context, alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            assert am != null;
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            assert am != null;
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

}
