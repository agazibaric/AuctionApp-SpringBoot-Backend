package com.agazibaric.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private float price;
    private LocalDateTime creationTime;
    private Duration duration;

}
