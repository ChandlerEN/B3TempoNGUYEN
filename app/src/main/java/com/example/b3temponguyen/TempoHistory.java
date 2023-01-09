package com.example.b3temponguyen;

import static com.example.b3temponguyen.MainActivity.edfApi;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.b3temponguyen.databinding.ActivityHistoryBinding;
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
}
