package com.example.elmsbackend.repository;

import com.example.elmsbackend.model.Classroom;
import com.example.elmsbackend.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassroomRepository extends MongoRepository<Classroom, String> {

    Classroom findClassroomById(String id);

}
