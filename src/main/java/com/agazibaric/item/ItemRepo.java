package com.agazibaric.item;

import com.agazibaric.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "items", collectionResourceRel = "items")
public interface ItemRepo extends JpaRepository<Item, Long> {

    List<Item> findByIsExpiredFalse();
    List<Item> findByisExpiredFalseAndUser(User user);
    //List<Item> findByIsExpiredFalseAndUserNot(User user);
    //@Query("select i from Item i where not i.isExpired and i.user.username != ?1")
    //List<Item> findByIsExpiredFalseAndUsernameNot(String username);

}
