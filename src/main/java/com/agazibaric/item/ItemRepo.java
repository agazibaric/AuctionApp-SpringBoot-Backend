package com.agazibaric.item;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(path = "items", collectionResourceRel = "items")
public interface ItemRepo extends PagingAndSortingRepository<Item, Long> {
}