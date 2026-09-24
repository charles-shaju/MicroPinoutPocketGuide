package com.example.micro_pinoutpocketguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.ComponentViewHolder> {

    private List<HardwareComponent> components = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(HardwareComponent component);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setComponents(List<HardwareComponent> components) {
        this.components = components != null ? components : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ComponentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_component, parent, false);
        return new ComponentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComponentViewHolder holder, int position) {
        HardwareComponent component = components.get(position);
        holder.bind(component, listener);
    }

    @Override
    public int getItemCount() {
        return components.size();
    }

    static class ComponentViewHolder extends RecyclerView.ViewHolder {
        private final TextView textComponentName;
        private final TextView textOperatingVoltage;

        public ComponentViewHolder(@NonNull View itemView) {
            super(itemView);
            textComponentName = itemView.findViewById(R.id.textComponentName);
            textOperatingVoltage = itemView.findViewById(R.id.textOperatingVoltage);
        }

        public void bind(HardwareComponent component, OnItemClickListener listener) {
            textComponentName.setText(component.getName());
            textOperatingVoltage.setText("Voltage: " + component.getOperatingVoltage());
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(component);
                }
            });
        }
    }
}
