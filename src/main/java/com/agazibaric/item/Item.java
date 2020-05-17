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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Item class represents an auction item on which users can bid on.
 * User can not bid on his items.
 * If the user is a highest bidder on an item,
 * <code>highestBidderPrice</code> is set to the bid amount that the user set.
 * <code>highestBidderPrice</code> is not visible to the other users,
 * only the <code>bidPrice</code> is shown to all users and is set to zero at the beginning.
 * By bidding, other users increase <code>bidPrice</code> hoping that their bid price
 * will exceed <code>highestBidderPrice</code>. By exceeding that price
 * the user become current highest bidder and
 * the <code>highestBidderPrice</code> is set to the user's bid price.
 * After that, <code>bidPrice</code> is set to the previous <code>highestBidderPrice</code>.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Float highestBidderPrice;

    @NotNull
    private Integer numberOfBids;

    @DateTimeFormat
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern="dd/MM/yyyy hh:mm")
    private LocalDateTime creationTime;

    @DurationFormat(value = DurationStyle.SIMPLE)
    private Duration duration;

    @NotNull
    private Boolean isExpired;

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

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> images;

}
