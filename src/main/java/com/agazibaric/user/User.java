package com.agazibaric.user;

import com.agazibaric.item.Item;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = "items")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Item> items;
    @OneToMany(mappedBy = "highestBidder", cascade = CascadeType.ALL)
    private List<Item> bids;

}
