package com.example.elmsbackend.repository;

import com.example.elmsbackend.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LibraryRepository extends MongoRepository<Library, String> {

    // find resource by Id
    Library findLibraryById(String id);

    List<Library> findLibraryByResourceType(String type);
}
