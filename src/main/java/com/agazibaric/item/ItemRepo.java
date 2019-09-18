package com.agazibaric.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource(path = "items", collectionResourceRel = "items")
@CrossOrigin(origins="*", maxAge=3600)
public interface ItemRepo extends JpaRepository<Item, Long> {

    List<Item> findByIsExpiredFalse();

}
