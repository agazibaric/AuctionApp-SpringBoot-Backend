package com.agazibaric.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
