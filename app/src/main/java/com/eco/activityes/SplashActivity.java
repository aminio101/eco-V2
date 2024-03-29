package com.eco.activityes;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.eco.CoustomTextView;
import com.eco.PV;
import com.eco.R;
import com.eco.views.DialogConnection;
import com.google.android.gms.common.GoogleApiAvailability;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {
    Dialog dialog;
    String mainVersion;
    String lastVersion;
    CoustomTextView cancel, download;
    private static final int MULTIPLE_PERMISSION_REQUEST_CODE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        setOnClick();
        getData();
    }

    private void getData() {
        new GetContacts().execute();
    }


    void init() {
        dialog = new Dialog(SplashActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update);
        cancel = dialog.findViewById(R.id.cancel);
        download = dialog.findViewById(R.id.download);
    }

    void setOnClick() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!PV.mainVersion.equals(mainVersion)) {
                    Toast.makeText(getApplicationContext(), "این نسخه پشتیبانی نمی شود", Toast.LENGTH_LONG).show();
                } else {
                    loadMain();
                }
                dialog.dismiss();
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(PV.versionEntity.eco_android_link));
                startActivity(browserIntent);
            }
        });
    }

    void loadMain() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    @SuppressLint("StaticFieldLeak")
    public class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(PV.urlCheckVersion)
                    .get()
                    .build();
            try {
                Response response = client.newCall(request).execute();
                assert response.body() != null;
                String data = response.body().string();
                JSONObject jsonObj = new JSONObject(data);
                PV.versionEntity.apiUrl = jsonObj.getString("apiUrl");
                PV.versionEntity.eco_android = jsonObj.getString("eco_android");
                PV.versionEntity.eco_android_link = jsonObj.getString("eco_android_link");
                PV.versionEntity.challenge_url_page = jsonObj.getString("challenge_url_page");
                PV.versionEntity.challenge_number = jsonObj.getInt("challenge_number");
            } catch (final IOException e) {
                Handler handler = new Handler(Looper.getMainLooper());

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("splashError", e.getMessage());
                        DialogConnection dialogConnection = new DialogConnection(SplashActivity.this, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getData();

                            }
                        });
                    }
                }, 0);

                e.printStackTrace();
            } catch (JSONException e) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DialogConnection dialogConnection = new DialogConnection(SplashActivity.this, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getData();
                            }
                        });
                    }
                }, 0);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (PV.versionEntity.apiUrl != null) {
                char charAtZero = PV.versionEntity.apiUrl.charAt(7);
                if (charAtZero != '/') {
                    PV.PROTOCOL = PV.versionEntity.apiUrl.substring(0, 7);
                    PV.URL = PV.versionEntity.apiUrl.substring(7);
                }
                mainVersion = PV.versionEntity.eco_android.substring(0, 1);
                lastVersion = PV.versionEntity.eco_android.substring(2);
                if (PV.mainVersion.equals(mainVersion) && PV.lasteVrsion.equals(lastVersion))
                    getPermission();
                else {
                    dialog.show();
                }
            }
        }

        private void getPermission() {
            int internetPermissionCheck = ContextCompat.checkSelfPermission(SplashActivity.this,
                    Manifest.permission.INTERNET);

            int networkStatePermissionCheck = ContextCompat.checkSelfPermission(SplashActivity.this,
                    Manifest.permission.ACCESS_NETWORK_STATE);

            int writeExternalStoragePermissionCheck = ContextCompat.checkSelfPermission(SplashActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            int coarseLocationPermissionCheck = ContextCompat.checkSelfPermission(SplashActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);

            int fineLocationPermissionCheck = ContextCompat.checkSelfPermission(SplashActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION);

            int wifiStatePermissionCheck = ContextCompat.checkSelfPermission(SplashActivity.this,
                    Manifest.permission.ACCESS_WIFI_STATE);

            if (internetPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                    networkStatePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                    writeExternalStoragePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                    coarseLocationPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                    fineLocationPermissionCheck == PackageManager.PERMISSION_GRANTED &&
                    wifiStatePermissionCheck == PackageManager.PERMISSION_GRANTED) {
                try {
                    goToLoginActivity();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{
                                Manifest.permission.INTERNET,
                                Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_WIFI_STATE},
                        MULTIPLE_PERMISSION_REQUEST_CODE);

                try{
                    goToLoginActivity();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        private void goToLoginActivity() throws PackageManager.NameNotFoundException {
            int v = getPackageManager().getPackageInfo(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE, 0).versionCode;
            Log.i("amirhosen", "" + v);
            int apkVersion = GoogleApiAvailability.getInstance().getApkVersion(SplashActivity.this);
            Log.i("amirhosen", "" + apkVersion);

            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
