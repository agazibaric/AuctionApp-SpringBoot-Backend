package com.agazibaric;

import com.agazibaric.item.Item;
import com.agazibaric.item.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class AuctionAppMain {

    @Autowired
    private ItemRepo repo;

    @Component
    class DataSetup implements ApplicationRunner {
        @Override
        public void run(ApplicationArguments args) throws Exception {
            repo.save(Item.builder()
                    .name("Fender Stratocaster")
                    .description("1968")
                    .price(1000f)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofDays(2))
                    .build());
            repo.save(Item.builder()
                    .name("Fender Telecaster")
                    .description("1968")
                    .price(1000f)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofDays(2))
                    .build());
            repo.save(Item.builder()
                    .name("Gibson Les Paul")
                    .description("1968")
                    .price(1000f)
                    .creationTime(LocalDateTime.now())
                    .duration(Duration.ofDays(2))
                    .build());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AuctionAppMain.class, args);
    }

}
