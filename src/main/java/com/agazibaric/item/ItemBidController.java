package com.agazibaric.item;

import com.agazibaric.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/bid")
public class ItemBidController {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public void makeABid(@RequestParam(name = "itemId") Long itemId, @RequestParam(name = "bid") Float bid) {
        if (itemId == null || bid == null || bid <= 0.0f) return;

        Optional<Item> op = itemRepo.findById(itemId);
        if (!op.isPresent()) return;

        Item item = op.get();
        Float currentBid = item.getBidPrice();
        if (currentBid == null || currentBid < bid) {
            item.setBidPrice(bid);
            //item.setHighestBidder(signedInUser);
        }
    }


}
