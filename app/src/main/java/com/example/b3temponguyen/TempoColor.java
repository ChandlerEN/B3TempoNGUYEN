package com.example.b3temponguyen;

import com.google.gson.annotations.SerializedName;

public enum TempoColor {
    @SerializedName("TEMPO_ROUGE")
    RED(R.color.tempo_red_day_bg, R.string.Rouge),

    @SerializedName("TEMPO_BLANC")
    WHITE(R.color.tempo_white_day_bg, R.string.Blanc),

    @SerializedName("TEMPO_BLEU")
    BLUE(R.color.tempo_blue_day_bg, R.string.Bleu),

    @SerializedName("NON_DEFINI")
    UNKNOWN(R.color.tempo_undecided_day_bg, R.string.Inconnue);

    private int resId;
    private int stringResId;

    TempoColor(int resId, int stringResId) {
        this.resId = resId;
        this.stringResId = stringResId;
    }

    public int getColorResId() {
        return resId;
    }

    public int getStringResId(){
        return stringResId;
    }
}
