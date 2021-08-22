package com.example.elmsbackend.repository;

import com.example.elmsbackend.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, String> {
}
