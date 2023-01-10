package com.example.b3temponguyen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b3temponguyen.databinding.TempoDateItem2Binding;

import java.util.List;

public class TempoDateAdapter2 extends RecyclerView.Adapter<TempoDateAdapter2.TempoDateViewHolder> {

    private final List<TempoDate> tempoDates;
    private Context context;

    /**
     * Constructor
     */
    public TempoDateAdapter2(List<TempoDate> tempoDates, Context context) {
        this.tempoDates = tempoDates;
        this.context = context;
    }

    @NonNull
    @Override
    public TempoDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tempo_date_item_2, parent, false);
        TempoDateItem2Binding binding = TempoDateItem2Binding.bind(v);
        return new TempoDateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TempoDateAdapter2.TempoDateViewHolder holder, int position) {

        /*holder.dateTv.setText(tempoDates.get(position).getDate());
        holder.colorFl.setBackgroundColor(ContextCompat.getColor(context, tempoDates.get(position).getCouleur().getResId()));*/

        holder.binding.dateTv.setText(tempoDates.get(position).getDate());
        holder.binding.colorName.setText(tempoDates.get(position).getCouleur().getStringResId());
        holder.binding.colorFl.setBackgroundColor(ContextCompat.getColor(context, tempoDates.get(position).getCouleur().getColorResId()));
    }

    @Override
    public int getItemCount() {
        return tempoDates.size();
    }

    public class TempoDateViewHolder extends RecyclerView.ViewHolder {
        TempoDateItem2Binding binding;

        public TempoDateViewHolder(@NonNull TempoDateItem2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
