package com.example.elmsbackend.controller;

import com.example.elmsbackend.repository.LibraryRepository;
import com.example.elmsbackend.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

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

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadTemplate(@PathVariable String id) throws IOException {
        byte[] file = service.downloadFile(id);

        //get filename and content type
        HashMap<String, String> details = service.getDetailsOfFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(details.get("contentType")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + details.get("filename") + "\"")
                .body(new ByteArrayResource(file));
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

    @GetMapping("/downloadResource/{id}")
    public ResponseEntity<ByteArrayResource> downloadLecture(@PathVariable String id) throws IOException {
        byte[] resource = service.downloadResource(id);

        //get filename and content type
        HashMap<String, String> res = service.getDetailsOfResource(id);

        return ResponseEntity.ok()

                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + res.get("filename") + "\"")
                .body(new ByteArrayResource(resource));
    }
}
