package com.example.b3temponguyen;

import static com.example.b3temponguyen.MainActivity.edfApi;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.b3temponguyen.databinding.ActivityMainBinding;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class was created with a POJO generator (http://www.jsonschema2pojo.org/) from the
 * following JSON sample:
 *
 * {"dates":
 *  [
 *   {"date":"2020-09-01","couleur":"TEMPO_BLEU"},
 *   {"date":"2020-09-02","couleur":"TEMPO_BLEU"},
 *   {"date":"2021-01-30","couleur":"TEMPO_BLEU"}
 *  ]
 * }
 *
 */
public class TempoHistory {
    @SerializedName("dates")
    @Expose
    private List<TempoDate> dates = null;

    public List<TempoDate> getTempoDates() {
        return dates;
    }

    public void setTempoDates(List<TempoDate> dates) {
        this.dates = dates;
    }

    public static void getTempoHistory(String LOG_TAG, IEdfApi edfApi, List<TempoDate> tempoDates, TempoDateAdapter tempoDateAdapter){
        Call<TempoHistory> call = edfApi.getTempoHistory("2022", "2023");

        call.enqueue(new Callback<TempoHistory>() {
            @Override
            public void onResponse(Call<TempoHistory> call, Response<TempoHistory> response) {
                tempoDates.clear();
                if (response.code() == HttpURLConnection.HTTP_OK && response.body() != null)
                {
                    tempoDates.addAll(response.body().getTempoDates());
                    Log.d(LOG_TAG, "nb elements = " + tempoDates.size());
                }
                tempoDateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<TempoHistory> call, @NonNull Throwable t) {
                Log.e(LOG_TAG, "call to getTempoHistory() failed ");
            }
        });
    }
}
