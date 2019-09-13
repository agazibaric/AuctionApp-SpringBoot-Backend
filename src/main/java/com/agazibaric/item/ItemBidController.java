package com.agazibaric.item;

import com.agazibaric.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ItemBidController {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = "/bid", method = RequestMethod.GET)
    public void makeABid(@RequestParam(value = "itemId") Long itemId,
                         @RequestParam(value = "bid") Float bid, Principal principal) {


        if (itemId == null || bid == null || bid <= 0.0f) return;

        Optional<Item> op = itemRepo.findById(itemId);
        if (!op.isPresent()) return;

        Item item = op.get();
        Float currentBid = item.getBidPrice();
        if (currentBid == null || currentBid < bid) {
            item.setBidPrice(bid);
            item.setHighestBidder(userRepo.findByUsername(principal.getName()));
            item.setNumberOfBids(item.getNumberOfBids() + 1);
            itemRepo.save(item);
        }
    }


}
