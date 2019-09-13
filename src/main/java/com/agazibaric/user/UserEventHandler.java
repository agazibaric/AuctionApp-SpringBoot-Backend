package com.agazibaric.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RepositoryEventHandler
public class UserEventHandler {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
     
    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setBids(new ArrayList<>());
        user.setItems(new ArrayList<>());
        user.setItemsWon(new ArrayList<>());
    }

}
