package com.agazibaric.entity;

import java.time.Duration;
import java.time.LocalDateTime;

public class Item {

    private int id;
    private String name;
    private String description;
    private float price;
    private LocalDateTime creationTime;
    private Duration duration;

    public Item(int id, String name, String description, float price, LocalDateTime creationTime, Duration duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationTime = creationTime;
        this.duration = duration;
    }

    public Item() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
