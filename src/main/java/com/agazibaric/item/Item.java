package com.agazibaric.item;

import com.agazibaric.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    private Float minimumPrice;

    @NotNull
    private Float bidPrice;

    @NotNull
    private Integer numberOfBids;

    @DateTimeFormat
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="dd/MM/yyyy hh:mm")
    private LocalDateTime creationTime;

    @DurationFormat(value = DurationStyle.SIMPLE)
    private Duration duration;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnoreProperties("items")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = true)
    @JsonIgnoreProperties("bids")
    private User highestBidder;

    @ManyToOne
    @JoinColumn(nullable = true)
    @JsonIgnoreProperties("itemsWon")
    private User winner;

}
