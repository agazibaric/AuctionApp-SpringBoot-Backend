package com.agazibaric;

import com.agazibaric.item.Item;
import com.agazibaric.item.ItemRepo;
import com.agazibaric.user.User;
import com.agazibaric.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AuctionAppMain extends SpringBootServletInitializer {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Component
    class DataSetup implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            Item i1 = Item.builder()
                    .name("Fender Stratocaster")
                    .description("1968")
                    .minimumPrice(1000f)
                    .bidPrice(0.0f)
                    .numberOfBids(0)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofHours(12))
                    .build();
            Item i2 = Item.builder()
                    .name("Fender Telecaster")
                    .description("1968")
                    .minimumPrice(1000f)
                    .bidPrice(0.0f)
                    .numberOfBids(0)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofDays(3))
                    .build();

            Item i3 = Item.builder()
                    .name("Fender Telecaster")
                    .description("1968")
                    .minimumPrice(1000f)
                    .bidPrice(0.0f)
                    .numberOfBids(0)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofDays(1))
                    .build();

            Item i4 = Item.builder()
                    .name("Fender Telecaster")
                    .description("1968")
                    .minimumPrice(1000f)
                    .bidPrice(0.0f)
                    .numberOfBids(0)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofSeconds(60))
                    .build();

            List<Item> items = new ArrayList<>();
            items.add(i1);
            items.add(i2);
            items.add(i3);
            items.add(i4);

            List<Item> bids = new ArrayList<>();
            bids.add(i1);

            User user = User.builder()
                    .items(items)
                    .bids(bids)
                    .itemsWon(new ArrayList<>())
                    .username("nick")
                    .password(bCryptPasswordEncoder.encode("pass"))
                    .build();

            items.forEach(i -> i.setUser(user));
            bids.forEach(b -> b.setHighestBidder(user));
            userRepo.save(user);

        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AuctionAppMain.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(AuctionAppMain.class, args);
    }

}
