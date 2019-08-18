package com.agazibaric.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private float price;
    private LocalDateTime creationTime;
    private Duration duration;

    public Item(String name, String description, float price, LocalDateTime creationTime, Duration duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.creationTime = creationTime;
        this.duration = duration;
    }
}
