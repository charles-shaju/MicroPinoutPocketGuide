package com.example.micro_pinoutpocketguide;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private PinAdapter pinAdapter;
    private List<Pin> allPins = new ArrayList<>();
    private String imageResName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String componentId = getIntent().getStringExtra("component_id");

        HardwareRepository repository = new HardwareRepository();
        List<HardwareComponent> components = repository.getComponents(this);

        HardwareComponent targetComponent = null;
        for (HardwareComponent component : components) {
            if (component.getId() != null && component.getId().equals(componentId)) {
                targetComponent = component;
                break;
            }
        }

        ImageView imageBoardDiagram = findViewById(R.id.imageBoardDiagram);

        if (targetComponent != null) {
            setTitle(targetComponent.getName());
            allPins = targetComponent.getPins() != null ? targetComponent.getPins() : new ArrayList<>();
            imageResName = targetComponent.getImageResName();

            int resId = getResources().getIdentifier(imageResName, "drawable", getPackageName());
            if (resId != 0) {
                imageBoardDiagram.setImageResource(resId);
            }
        }

        imageBoardDiagram.setOnClickListener(v -> {
            if (imageResName != null) {
                Intent intent = new Intent(DetailActivity.this, ImageViewerActivity.class);
                intent.putExtra("image_name", imageResName);
                startActivity(intent);
            }
        });

        RecyclerView recyclerViewPins = findViewById(R.id.recyclerViewPins);
        recyclerViewPins.setLayoutManager(new LinearLayoutManager(this));
        pinAdapter = new PinAdapter();
        recyclerViewPins.setAdapter(pinAdapter);
        pinAdapter.setPins(allPins);

        Button btnFilterAll = findViewById(R.id.btnFilterAll);
        Button btnFilterPower = findViewById(R.id.btnFilterPower);
        Button btnFilterGround = findViewById(R.id.btnFilterGround);
        Button btnFilterDigital = findViewById(R.id.btnFilterDigital);
        Button btnFilterGpio = findViewById(R.id.btnFilterGpio);
        Button btnFilterPwm = findViewById(R.id.btnFilterPwm);
        Button btnFilterI2c = findViewById(R.id.btnFilterI2c);
        Button btnFilterUart = findViewById(R.id.btnFilterUart);
        Button btnFilterAnalog = findViewById(R.id.btnFilterAnalog);

        btnFilterAll.setOnClickListener(v -> filterPins(null));
        btnFilterPower.setOnClickListener(v -> filterPins("Power"));
        btnFilterGround.setOnClickListener(v -> filterPins("Ground"));
        btnFilterDigital.setOnClickListener(v -> filterPins("GPIO")); // Digital maps to GPIO
        btnFilterGpio.setOnClickListener(v -> filterPins("GPIO"));
        btnFilterPwm.setOnClickListener(v -> filterPins("PWM"));
        btnFilterI2c.setOnClickListener(v -> filterPins("I2C"));
        btnFilterUart.setOnClickListener(v -> filterPins("UART"));
        btnFilterAnalog.setOnClickListener(v -> filterPins("Analog"));
    }

    private void filterPins(String type) {
        if (type == null) {
            pinAdapter.setPins(allPins);
            return;
        }

        List<Pin> filtered = new ArrayList<>();
        for (Pin pin : allPins) {
            if (pin.getType() != null && pin.getType().equalsIgnoreCase(type)) {
                filtered.add(pin);
            }
        }
        pinAdapter.setPins(filtered);
    }
}
