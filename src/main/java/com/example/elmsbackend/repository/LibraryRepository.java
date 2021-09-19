package com.example.elmsbackend.repository;

import com.example.elmsbackend.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LibraryRepository extends MongoRepository<Library, String> {

    // find resource by Id
    Library findLibraryById(String id);

    // find resource by type
    List<Library> findLibraryByResourceType(String type);

    @Query("{ $or :[ { grade: { $regex : ?0 , $options: 'i' } }, { subject: { $regex : ?0 , $options: 'i' } }, { fileName: { $regex : ?0 , $options: 'i' } } ] }")
    List<Library> findLibraryResource(String search);
}
