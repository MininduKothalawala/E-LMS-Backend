package com.example.elmsbackend.repository;


import com.example.elmsbackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AdminUserRepository extends MongoRepository<User, String> {

    @Query("{'name' : ?0}")
    Optional<User> findByUsername (String name);


}
