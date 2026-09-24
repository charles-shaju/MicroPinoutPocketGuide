package com.example.micro_pinoutpocketguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PinAdapter extends RecyclerView.Adapter<PinAdapter.PinViewHolder> {

    private List<Pin> pins = new ArrayList<>();

    public void setPins(List<Pin> pins) {
        this.pins = pins != null ? pins : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pin, parent, false);
        return new PinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PinViewHolder holder, int position) {
        Pin pin = pins.get(position);
        holder.bind(pin);
    }

    @Override
    public int getItemCount() {
        return pins.size();
    }

    static class PinViewHolder extends RecyclerView.ViewHolder {
        private final TextView textPinNumber;
        private final TextView textPinName;
        private final TextView textPinType;

        public PinViewHolder(@NonNull View itemView) {
            super(itemView);
            textPinNumber = itemView.findViewById(R.id.textPinNumber);
            textPinName = itemView.findViewById(R.id.textPinName);
            textPinType = itemView.findViewById(R.id.textPinType);
        }

        public void bind(Pin pin) {
            textPinNumber.setText(pin.getPinNumber());
            textPinName.setText(pin.getName());
            textPinType.setText(pin.getType());
        }
    }
}
