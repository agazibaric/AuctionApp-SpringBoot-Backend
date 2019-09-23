package com.agazibaric.item;

import com.agazibaric.user.User;
import com.agazibaric.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@RepositoryEventHandler
public class ItemEventHandler {

    @Autowired
    private UserRepo userRepo;
     
    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(Item item){
        User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        item.setUser(user);
        item.setCreationTime(LocalDateTime.now());
        item.setNumberOfBids(0);
        item.setBidPrice(0f);
        item.setHighestBidder(null);
        item.setWinner(null);
        item.setIsExpired(false);
        item.setImages(new ArrayList<>());
    }

}
