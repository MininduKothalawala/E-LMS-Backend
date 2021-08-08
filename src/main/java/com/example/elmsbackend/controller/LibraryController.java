package com.example.elmsbackend.controller;

import com.example.elmsbackend.repository.LibraryRepository;
import com.example.elmsbackend.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/library")
public class LibraryController {

    private final LibraryRepository repository;

    private final LibraryService service;

    @Autowired
    public LibraryController(LibraryRepository repository, LibraryService service) {
        this.repository = repository;
        this.service = service;
    }

    // add resource
    @PostMapping("/")
    public ResponseEntity<?> addResource(@RequestParam("resourceType") String resourceType, @RequestParam("grade") String grade, @RequestParam("subject") String subject,
                                         @RequestParam("file")MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(service.addResource(resourceType, grade, subject, multipartFile));
    }

    // get all resources
    @GetMapping("/")
    public ResponseEntity<?> getAllLibraries() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    // get a resource
    @GetMapping("/{id}")
    public ResponseEntity<?> getLibrary(@PathVariable String id) {
        return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
    }


    // update a resource
    @PutMapping("/edit")
    public ResponseEntity<?> updateResource(@RequestParam("id") String id, @RequestParam("resourceType") String resourceType, @RequestParam("grade") String grade, @RequestParam("subject") String subject,
                                         @RequestParam("file")MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(service.updateResource(id, resourceType, grade, subject, multipartFile));
    }

    //delete a resource
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable String id) {
        return ResponseEntity.ok(service.deleteResource(id));
    }
}
