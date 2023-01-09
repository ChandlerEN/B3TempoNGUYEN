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
}
