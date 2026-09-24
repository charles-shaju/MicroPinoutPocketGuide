package com.example.micro_pinoutpocketguide;

import java.util.List;

public class HardwareComponent {
    private String id;
    private String name;
    private String operatingVoltage;
    private String imageResName;
    private List<Pin> pins;

    public HardwareComponent() {}

    public HardwareComponent(String id, String name, String operatingVoltage, String imageResName, List<Pin> pins) {
        this.id = id;
        this.name = name;
        this.operatingVoltage = operatingVoltage;
        this.imageResName = imageResName;
        this.pins = pins;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatingVoltage() {
        return operatingVoltage;
    }

    public void setOperatingVoltage(String operatingVoltage) {
        this.operatingVoltage = operatingVoltage;
    }

    public String getImageResName() {
        return imageResName;
    }

    public void setImageResName(String imageResName) {
        this.imageResName = imageResName;
    }

    public List<Pin> getPins() {
        return pins;
    }

    public void setPins(List<Pin> pins) {
        this.pins = pins;
    }
}
