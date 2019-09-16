package com.agazibaric.user;

import com.agazibaric.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"items", "bids", "itemsWon"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @Transient
    private String passwordConfirm;

    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "user", "highestBidder", "winner"})
    private List<Item> items;

    @OneToMany(mappedBy = "highestBidder", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "user", "highestBidder", "winner"})
    private List<Item> bids;

    @OneToMany(mappedBy = "winner", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "user", "highestBidder", "winner"})
    private List<Item> itemsWon;

}
