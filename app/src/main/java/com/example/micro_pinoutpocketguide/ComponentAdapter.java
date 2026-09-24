package com.example.micro_pinoutpocketguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.ComponentViewHolder> {

    private List<HardwareComponent> components = new ArrayList<>();
    private Set<String> favoriteIds;
    private OnItemClickListener itemClickListener;
    private OnFavoriteClickListener favoriteClickListener;

    public interface OnItemClickListener {
        void onItemClick(HardwareComponent component);
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(HardwareComponent component);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnFavoriteClickListener(OnFavoriteClickListener listener) {
        this.favoriteClickListener = listener;
    }

    public void setComponents(List<HardwareComponent> components, Set<String> favoriteIds) {
        this.components = components != null ? components : new ArrayList<>();
        this.favoriteIds = favoriteIds;
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
        boolean isFav = favoriteIds != null && favoriteIds.contains(component.getId());
        holder.bind(component, isFav, itemClickListener, favoriteClickListener);
    }

    @Override
    public int getItemCount() {
        return components.size();
    }

    static class ComponentViewHolder extends RecyclerView.ViewHolder {
        private final TextView textComponentName;
        private final TextView textOperatingVoltage;
        private final ImageView imageFavorite;

        public ComponentViewHolder(@NonNull View itemView) {
            super(itemView);
            textComponentName = itemView.findViewById(R.id.textComponentName);
            textOperatingVoltage = itemView.findViewById(R.id.textOperatingVoltage);
            imageFavorite = itemView.findViewById(R.id.imageFavorite);
        }

        public void bind(HardwareComponent component, boolean isFavorite,
                         OnItemClickListener itemListener, OnFavoriteClickListener favListener) {
            textComponentName.setText(component.getName());
            textOperatingVoltage.setText("Voltage: " + component.getOperatingVoltage());

            if (isFavorite) {
                imageFavorite.setImageResource(android.R.drawable.btn_star_big_on);
            } else {
                imageFavorite.setImageResource(android.R.drawable.btn_star_big_off);
            }

            itemView.setOnClickListener(v -> {
                if (itemListener != null) {
                    itemListener.onItemClick(component);
                }
            });

            imageFavorite.setOnClickListener(v -> {
                if (favListener != null) {
                    favListener.onFavoriteClick(component);
                }
            });
        }
    }
}
