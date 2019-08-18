package com.agazibaric;

import com.agazibaric.dao.AuctionDao;
import com.agazibaric.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDateTime;

@Configuration
@Slf4j
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(AuctionDao repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Item("Guitar1", "Fender", 1000f, LocalDateTime.now(), Duration.ofHours(2L))));
      log.info("Preloading " + repository.save(new Item("Guitar2", "Fender", 1000f, LocalDateTime.now(), Duration.ofHours(2L))));
      log.info("Preloading " + repository.save(new Item("Guitar3", "Fender", 1000f, LocalDateTime.now(), Duration.ofHours(2L))));
    };
  }
}