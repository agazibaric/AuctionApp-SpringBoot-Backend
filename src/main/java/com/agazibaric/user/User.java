package com.agazibaric.user;

import com.agazibaric.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "user", "highestBidder", "winner"})
    private List<Item> items;

    @OneToMany(mappedBy = "highestBidder", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "user", "highestBidder", "winner"})
    private List<Item> bids;

    @OneToMany(mappedBy = "winner", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "user", "highestBidder", "winner"})
    private List<Item> itemsWon;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof User)) return false;
        User u = (User) o;
        return u.getUsername().equals(this.username);
    }

}
