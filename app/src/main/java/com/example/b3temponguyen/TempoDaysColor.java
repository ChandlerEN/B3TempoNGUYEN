package com.example.b3temponguyen;

import static com.example.b3temponguyen.Tools.getNowDate;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.b3temponguyen.databinding.ActivityMainBinding;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TempoDaysColor {
    @SerializedName("couleurJourJ")
    @Expose
    private TempoColor couleurJourJ;
    @SerializedName("couleurJourJ1")
    @Expose
    private TempoColor couleurJourJ1;

    public TempoColor getCouleurJourJ() {
        return couleurJourJ;
    }

    public void setCouleurJourJ(TempoColor couleurJourJ) {
        this.couleurJourJ = couleurJourJ;
    }

    public TempoColor getCouleurJourJ1() {
        return couleurJourJ1;
    }

    public void setCouleurJourJ1(TempoColor couleurJourJ1) {
        this.couleurJourJ1 = couleurJourJ1;
    }

    public static void getTempoDaysColor(String LOG_TAG, IEdfApi edfApi, ActivityMainBinding binding){
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
                    /*MainActivity.checkColor4Notif(tempoDaysColor.getCouleurJourJ1());*/
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
}