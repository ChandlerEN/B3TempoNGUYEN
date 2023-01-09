package com.example.b3temponguyen;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.b3temponguyen.databinding.ActivityMainBinding;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TempoDaysLeft {
    @SerializedName("PARAM_NB_J_BLANC")
    @Expose
    private Integer paramNbJBlanc;
    @SerializedName("PARAM_NB_J_ROUGE")
    @Expose
    private Integer paramNbJRouge;
    @SerializedName("PARAM_NB_J_BLEU")
    @Expose
    private Integer paramNbJBleu;

    public Integer getParamNbJBlanc() {
        return paramNbJBlanc;
    }

    public void setParamNbJBlanc(Integer paramNbJBlanc) {
        this.paramNbJBlanc = paramNbJBlanc;
    }

    public Integer getParamNbJRouge() {
        return paramNbJRouge;
    }

    public void setParamNbJRouge(Integer paramNbJRouge) {
        this.paramNbJRouge = paramNbJRouge;
    }

    public Integer getParamNbJBleu() {
        return paramNbJBleu;
    }

    public void setParamNbJBleu(Integer paramNbJBleu) {
        this.paramNbJBleu = paramNbJBleu;
    }

    public static void getTempoDaysLeft(String LOG_TAG, IEdfApi edfApi, ActivityMainBinding binding){
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
}
