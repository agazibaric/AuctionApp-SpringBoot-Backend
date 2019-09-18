package com.agazibaric.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ItemScheduler {

    @Autowired
    private ItemRepo itemRepo;

    @Scheduled(fixedDelay = 4000, initialDelay = 4000)
    public void checkItemExpiration() {
        for (Item item : itemRepo.findByIsExpiredFalse()) {
            if (isItemExpired(item)) {
                System.out.println("Item expired: " + item.getName());
                item.setWinner(item.getHighestBidder());
                item.setIsExpired(true);
                itemRepo.save(item);
            }
        }
    }

    private boolean isItemExpired(Item item) {
        return item.getCreationTime().plus(item.getDuration()).isBefore(LocalDateTime.now());
    }

}
