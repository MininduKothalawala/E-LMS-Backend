package com.example.elmsbackend.repository;

import com.example.elmsbackend.model.Notice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticeRepository extends MongoRepository<Notice, String> {
}
