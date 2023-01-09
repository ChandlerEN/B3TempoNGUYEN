package com.example.b3temponguyen;

import static com.example.b3temponguyen.Tools.getNowDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.b3temponguyen.databinding.ActivityMainBinding;

import java.net.HttpURLConnection;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final int ALARM_MANAGER_REQUEST_CODE = 2023;
    public static IEdfApi edfApi;
    ActivityMainBinding binding;

    private static  String CHANNEL_ID = "notifchannelID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);*/
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // init views
        binding.historyBt.setOnClickListener(this);
        
        // Create notification channel
        createNotificationChannel();

        // init Alarms

        // Init Retrofit client
        Retrofit retrofitClient = com.example.b3temponguyen.ApiClient.get();
        if (retrofitClient != null) {
            // Create EDF API Call interface
            edfApi = retrofitClient.create(IEdfApi.class);
        } else {
            Log.e(LOG_TAG, "unable to initialize Retrofit client");
            finish();
        }

        // Create call to getTempoDaysLeft
        getTempoDaysLeft(LOG_TAG, edfApi, binding);
        /*Call<TempoDaysLeft> call = edfApi.getTempoDaysLeft(IEdfApi.EDF_TEMPO_API_ALERT_TYPE);

        call.enqueue(new Callback<TempoDaysLeft>() {
            @Override
            public void onResponse(@NonNull Call<TempoDaysLeft> call, @NonNull Response<TempoDaysLeft> response) {
                TempoDaysLeft tempoDaysLeft = response.body();
                if (response.code() == HttpURLConnection.HTTP_OK && tempoDaysLeft != null) {
                    Log.d(LOG_TAG, "nb red days = " + tempoDaysLeft.getParamNbJRouge());
                    Log.d(LOG_TAG, "nb white days = " + tempoDaysLeft.getParamNbJBlanc());
                    Log.d(LOG_TAG, "nb blue days = " + tempoDaysLeft.getParamNbJBleu());
                    binding.blueDaysTv.setText(String.valueOf(tempoDaysLeft.getParamNbJBleu()));
                    binding.whiteDaysTv.setText(String.valueOf(tempoDaysLeft.getParamNbJBlanc()));
                    binding.redDaysTv.setText(String.valueOf(tempoDaysLeft.getParamNbJRouge()));
                } else {
                    Log.w(LOG_TAG, "call to getTempoDaysLeft () failed with error code " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TempoDaysLeft> call, @NonNull Throwable t) {
                Log.e(LOG_TAG, "call to getTempoDaysLeft () failed ");
            }
        });*/

        // Call to getTempoDaysColor
        getTempoDaysColor(LOG_TAG, edfApi, binding);
        /*Call<TempoDaysColor> call2 = edfApi.getTempoDaysColor(getNowDate("yyyy-MM-dd"),IEdfApi.EDF_TEMPO_API_ALERT_TYPE);

        call2.enqueue(new Callback<TempoDaysColor>() {
            @Override
            public void onResponse(@NonNull Call<TempoDaysColor> call, @NonNull Response<TempoDaysColor> response) {
                TempoDaysColor tempoDaysColor = response.body();
                if (response.code() == HttpURLConnection.HTTP_OK && tempoDaysColor != null) {
                    Log.d(LOG_TAG,"GetNowDate = " + getNowDate("yyyy.MM.dd"));
                    Log.d(LOG_TAG,"Today color = "+tempoDaysColor.getCouleurJourJ().toString());
                    Log.d(LOG_TAG,"Tomorrow color = "+tempoDaysColor.getCouleurJourJ1().toString());
                    binding.TodayDcv.setDayCircleColor(tempoDaysColor.getCouleurJourJ());
                    binding.TomorrowDcv.setDayCircleColor(tempoDaysColor.getCouleurJourJ1());
                    checkColor4Notif(tempoDaysColor.getCouleurJourJ1());
                } else {
                    Log.w(LOG_TAG, "call to getTempoDaysColor() failed with error code " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TempoDaysColor> call, @NonNull Throwable t) {
                Log.e(LOG_TAG, "call to getTempoDaysColor() failed ");
            }
        });*/
    }
    public void getTempoDaysLeft(String LOG_TAG, IEdfApi edfApi, ActivityMainBinding binding){
        Call<TempoDaysLeft> call = edfApi.getTempoDaysLeft(IEdfApi.EDF_TEMPO_API_ALERT_TYPE);

        call.enqueue(new Callback<TempoDaysLeft>() {
            @Override
            public void onResponse(@NonNull Call<TempoDaysLeft> call, @NonNull Response<TempoDaysLeft> response) {
                TempoDaysLeft tempoDaysLeft = response.body();
                if (response.code() == HttpURLConnection.HTTP_OK && tempoDaysLeft != null) {
                    Log.d(LOG_TAG, "nb red days = " + tempoDaysLeft.getParamNbJRouge());
                    Log.d(LOG_TAG, "nb white days = " + tempoDaysLeft.getParamNbJBlanc());
                    Log.d(LOG_TAG, "nb blue days = " + tempoDaysLeft.getParamNbJBleu());
                    binding.blueDaysTv.setText(String.valueOf(tempoDaysLeft.getParamNbJBleu()));
                    binding.whiteDaysTv.setText(String.valueOf(tempoDaysLeft.getParamNbJBlanc()));
                    binding.redDaysTv.setText(String.valueOf(tempoDaysLeft.getParamNbJRouge()));
                } else {
                    Log.w(LOG_TAG, "call to getTempoDaysLeft () failed with error code " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TempoDaysLeft> call, @NonNull Throwable t) {
                Log.e(LOG_TAG, "call to getTempoDaysLeft () failed ");
            }
        });
    }

    public void getTempoDaysColor(String LOG_TAG, IEdfApi edfApi, ActivityMainBinding binding){
        Call<TempoDaysColor> call2 = edfApi.getTempoDaysColor(getNowDate("yyyy-MM-dd"),IEdfApi.EDF_TEMPO_API_ALERT_TYPE);

        call2.enqueue(new Callback<TempoDaysColor>() {
            @Override
            public void onResponse(@NonNull Call<TempoDaysColor> call, @NonNull Response<TempoDaysColor> response) {
                TempoDaysColor tempoDaysColor = response.body();
                if (response.code() == HttpURLConnection.HTTP_OK && tempoDaysColor != null) {
                    Log.d(LOG_TAG,"GetNowDate = " + getNowDate("yyyy-MM-dd"));
                    Log.d(LOG_TAG,"Today color = "+tempoDaysColor.getCouleurJourJ().toString());
                    Log.d(LOG_TAG,"Tomorrow color = "+tempoDaysColor.getCouleurJourJ1().toString());
                    binding.TodayDcv.setDayCircleColor(tempoDaysColor.getCouleurJourJ());
                    binding.TomorrowDcv.setDayCircleColor(tempoDaysColor.getCouleurJourJ1());
                    checkColor4Notif(tempoDaysColor.getCouleurJourJ1());
                } else {
                    Log.w(LOG_TAG, "call to getTempoDaysColor() failed with error code " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TempoDaysColor> call, @NonNull Throwable t) {
                Log.e(LOG_TAG, "call to getTempoDaysColor() failed ");
            }
        });
    }

    // Create notification channel
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void checkColor4Notif(TempoColor nextdaycolor) {
        if(nextdaycolor == TempoColor.RED || nextdaycolor == TempoColor.WHITE) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getString(R.string.notifTitle))
                    .setContentText(getString(R.string.notifText, getString(nextdaycolor.getStringResId())))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(Tools.getNextNotifId(), builder.build());
        }
    }

    private void initAlarmManager() {
        // create a pending intent
        Intent intent = new Intent(this, TempoAlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(
                this,
                ALARM_MANAGER_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

        // Set the alarm to start at approximately 2:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 47);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this, HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTempoDaysLeft(LOG_TAG, edfApi, binding);
        getTempoDaysColor(LOG_TAG, edfApi, binding);
    }
}