package com.example.elmsbackend.repository;


import com.example.elmsbackend.model.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AdminUserRepository extends MongoRepository<AdminUser, String> {

    @Query("{'name' : ?0}")
    Optional<AdminUser> findByUsername (String name);


}
