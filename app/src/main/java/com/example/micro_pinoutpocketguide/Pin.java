package com.example.micro_pinoutpocketguide;

public class Pin {
    private String pinNumber;
    private String name;
    private String type;
    private String description;

    public Pin() {}

    public Pin(String pinNumber, String name, String type, String description) {
        this.pinNumber = pinNumber;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
