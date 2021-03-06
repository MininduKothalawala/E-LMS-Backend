package com.example.elmsbackend.repository;

import com.example.elmsbackend.model.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassroomRepository extends MongoRepository<Classroom, String> {

    Classroom findClassroomById(String id);

    List<Classroom> findByGrade (String grade);

    List<Classroom> findBySubject (String subject);

    List<Classroom> findByTopic (String topic);

    List<Classroom> findByAddedBy (String addedBy);

}
