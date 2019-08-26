package com.agazibaric.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRepo extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
