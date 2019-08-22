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
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class AuctionAppMain {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    @Component
    class DataSetup implements ApplicationRunner {
        @Override
        public void run(ApplicationArguments args) throws Exception {
            Item i1 = Item.builder()
                    .name("Fender Stratocaster")
                    .description("1968")
                    .price(1000f)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofDays(2))
                    .build();
            Item i2 = Item.builder()
                    .name("Fender Telecaster")
                    .description("1968")
                    .price(1000f)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofDays(2))
                    .build();

            List<Item> items = new ArrayList<>();
            items.add(i1);
            items.add(i2);

            User user = User.builder().items(items).username("Nick").password("Pass").build();
            items.forEach(i -> i.setUser(user));
            userRepo.save(user);

        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AuctionAppMain.class, args);
    }

}
