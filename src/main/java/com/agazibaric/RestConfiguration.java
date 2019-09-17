package com.agazibaric;

import com.agazibaric.item.Item;
import com.agazibaric.item.ItemRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class RestConfiguration implements RepositoryRestConfigurer {

    /** Time in milliseconds */
    private static final int EXPIRED_CHECK_RATE = 1000;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(
                entityManager.getMetamodel().getEntities().stream()
                .map(Type::getJavaType)
                .toArray(Class[]::new));
    }

   /* @Scheduled(fixedDelay = EXPIRED_CHECK_RATE)
    public void checkItemExpiration() {
        System.out.println("Checking expiration of all items...");
        List<Item> items = itemRepo.findByIsExpiredFalse();
        for (Item item : items) {
            if (checkIsExpired(item)) {
                item.setIsExpired(true);
                itemRepo.save(item);
                System.out.println("ITEM EXPIRED: " + item);
                System.out.println("CURRENT TIME: " + LocalDateTime.now());
            }
        }
    }
*/
    private boolean checkIsExpired(Item item) {
        return item.getCreationTime().plus(item.getDuration()).isBefore(LocalDateTime.now());
    }

}
