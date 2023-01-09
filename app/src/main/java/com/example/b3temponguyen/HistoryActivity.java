package com.example.b3temponguyen;

import static com.example.b3temponguyen.MainActivity.edfApi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.b3temponguyen.databinding.ActivityHistoryBinding;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    private static final String LOG_TAG = HistoryActivity.class.getSimpleName();

    // Init views
    ActivityHistoryBinding binding;

    // Data model
    List<TempoDate> tempoDates = new ArrayList<>();

    // RV adapter
    TempoDateAdapter tempoDateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Init recycler view
        binding.tempoHistoryRv.setHasFixedSize(true); // On dit au recycler view que tous les éléments ont la même taille. Le fait de le mettre à true veut dire que : les changements ne pourront pas affecter les changements de la recycler view.
        binding.tempoHistoryRv.setLayoutManager(new LinearLayoutManager(this)); // LinearLayoutManager qui permet d'arranger les élements sous forme de liste.
        tempoDateAdapter = new TempoDateAdapter(tempoDates, this);
        binding.tempoHistoryRv.setAdapter(tempoDateAdapter);
        binding.progressBar.setVisibility(View.VISIBLE);

        if (edfApi != null)
        {
            // Create call
            TempoHistory.getTempoHistory(LOG_TAG, edfApi, tempoDates, tempoDateAdapter, binding);
            /*Call<TempoHistory> call = edfApi.getTempoHistory("2022", "2023");

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

                }
            });*/
        }
    }
}