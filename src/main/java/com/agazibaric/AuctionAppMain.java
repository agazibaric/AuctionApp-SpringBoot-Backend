package com.agazibaric;

import com.agazibaric.item.ImageService;
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

    @Autowired
    private ImageService imageService;

    @Component
    class DataSetup implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            imageService.init();

            List<String> img1 = new ArrayList<>();
            List<String> img2 = new ArrayList<>();
            List<String> img3 = new ArrayList<>();
            List<String> img4 = new ArrayList<>();
            img1.add("strat.jpg");
            img2.add("telly1.png");
            img3.add("telly2.jpg");
            img4.add("classic.jpg");

            Item i1 = Item.builder()
                    .name("Fender Stratocaster")
                    .description("1968")
                    .minimumPrice(1000f)
                    .bidPrice(0.0f)
                    .numberOfBids(0)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofHours(12))
                    .isExpired(false)
                    .images(img1)
                    .build();

            Item i2 = Item.builder()
                    .name("Fender Telecaster")
                    .description("1968")
                    .minimumPrice(1000f)
                    .bidPrice(0.0f)
                    .numberOfBids(0)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofDays(3))
                    .isExpired(false)
                    .images(img2)
                    .build();

            Item i3 = Item.builder()
                    .name("Fender Telecaster")
                    .description("1968")
                    .minimumPrice(1000f)
                    .bidPrice(0.0f)
                    .numberOfBids(0)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofDays(1))
                    .isExpired(false)
                    .images(img3)
                    .build();

            Item i4 = Item.builder()
                    .name("Classical guitar")
                    .description("1968")
                    .minimumPrice(1000f)
                    .bidPrice(0.0f)
                    .numberOfBids(0)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofSeconds(60))
                    .isExpired(false)
                    .images(img4)
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
                    .email("ante1506@gmail.com")
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
