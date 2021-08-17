package com.example.elmsbackend.controller;

import com.example.elmsbackend.repository.ClassroomRepository;
import com.example.elmsbackend.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomController(ClassroomService classroomService, ClassroomRepository classroomRepository) {
        this.classroomService = classroomService;
        this.classroomRepository = classroomRepository;
    }

    @PostMapping("/addClassroom")
    public ResponseEntity<?> addClassroom( @RequestParam("grade") String grade, @RequestParam("subject") String subject, @RequestParam("topic") String topic,
                                          @RequestParam("description") String description, @RequestParam("date") String date, @RequestParam("time") String time, @RequestParam("link") String link,
                                          @RequestParam("addedBy") String addedBy, @RequestParam("lecFile") MultipartFile lecFile, @RequestParam("tuteFile") MultipartFile tuteFile, @RequestParam("classImg") MultipartFile classImg) throws IOException {
        classroomService.addClassroom(grade,subject,topic,description,date,time,link,addedBy,lecFile,tuteFile,classImg);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @PostMapping("/")
//    public ResponseEntity<?> addResource(@RequestParam("resourceType") String resourceType, @RequestParam("grade") String grade, @RequestParam("subject") String subject,
//                                         @RequestParam("file")MultipartFile multipartFile) throws IOException {
//        return ResponseEntity.ok(service.addResource(resourceType, grade, subject, multipartFile));
//    }

    @GetMapping("/")
    public ResponseEntity<?> getAllClassrooms() {
        return new ResponseEntity<>(classroomRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClassroom(@PathVariable String id) {
        return new ResponseEntity<>(classroomRepository.findById(id), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateClassroom(@RequestParam("id") String id, @RequestParam("grade") String grade, @RequestParam("subject") String subject, @RequestParam("topic") String topic,
                                             @RequestParam("description") String description, @RequestParam("date") String date, @RequestParam("time") String time, @RequestParam("link") String link,
                                             @RequestParam("addedBy") String addedBy, @RequestParam("lecFile") MultipartFile lecFile, @RequestParam("tuteFile") MultipartFile tuteFile, @RequestParam("classImg") MultipartFile classImg) throws IOException {
        return ResponseEntity.ok(classroomService.updateClassroom(id,grade,subject,topic,description,date,time,link,addedBy,lecFile,tuteFile,classImg));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassroom(@PathVariable String id) {
        return ResponseEntity.ok(classroomService.deleteClassroom(id));
    }


}
