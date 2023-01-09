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
}