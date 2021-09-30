package com.example.elmsbackend.controller;

import com.example.elmsbackend.model.Classroom;
import com.example.elmsbackend.repository.ClassroomRepository;
import com.example.elmsbackend.services.ClassroomService;
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
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<Classroom> addClassroom(@RequestParam("grade") String grade, @RequestParam("subject") String subject, @RequestParam("topic") String topic,
                                                  @RequestParam("description") String description, @RequestParam("date") String date, @RequestParam("time") String time, @RequestParam("link") String link,
                                                  @RequestParam("addedBy") String addedBy, @RequestParam("lecFile") MultipartFile lecFile, @RequestParam("tuteFile") MultipartFile tuteFile, @RequestParam("classImg") MultipartFile classImg) throws IOException {
        classroomService.addClassroom(grade, subject, topic, description, date, time, link, addedBy, lecFile, tuteFile, classImg);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Classroom>> getAllClassrooms() {
        return new ResponseEntity<>(classroomRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Optional<Classroom>> getClassroomById(@PathVariable String id) {
        return ResponseEntity.ok(classroomRepository.findById(id));
    }

    @GetMapping("/getbygrade/{grade}")
    public ResponseEntity<List<Classroom>> getClassroomByGrade(@PathVariable String grade) {
        return new ResponseEntity<>(classroomService.getClassroomByGrade(grade), HttpStatus.OK);
    }

    @GetMapping("/getbysubject/{subject}")
    public ResponseEntity<List<Classroom>> getClassroomBySubject(@PathVariable String subject) {
        return new ResponseEntity<>(classroomService.getClassroomBySubject(subject), HttpStatus.OK);
    }

    @GetMapping("/getbytopic/{topic}")
    public ResponseEntity<List<Classroom>> getClassroomByTopic(@PathVariable String topic) {
        return new ResponseEntity<>(classroomService.getClassroomByTopic(topic), HttpStatus.OK);
    }

    @GetMapping("/getbyaddedBy/{addedBy}")
    public ResponseEntity<List<Classroom>> getClassroomByAddedBy(@PathVariable String addedBy) {
        return new ResponseEntity<>(classroomRepository.findByAddedBy(addedBy), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Classroom> updateClassroom(@RequestParam("id") String id, @RequestParam("grade") String grade, @RequestParam("subject") String subject, @RequestParam("topic") String topic,
                                             @RequestParam("description") String description, @RequestParam("date") String date, @RequestParam("time") String time, @RequestParam("link") String link,
                                             @RequestParam("addedBy") String addedBy, @RequestParam("lecFile") MultipartFile lecFile, @RequestParam("tuteFile") MultipartFile tuteFile, @RequestParam("classImg") MultipartFile classImg) throws IOException {
        return ResponseEntity.ok(classroomService.updateClassroom(id, grade, subject, topic, description, date, time, link, addedBy, lecFile, tuteFile, classImg));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable String id) {
        return ResponseEntity.ok(classroomService.deleteClassroom(id));
    }

    @GetMapping("/downloadLec/{id}")
    public ResponseEntity<ByteArrayResource> downloadLecture(@PathVariable String id) throws IOException {
        byte[] lecture = classroomService.downloadLecture(id);

        //get filename and content type
        HashMap<String, String> lec = classroomService.getDetailsOfLecture(id);

        return ResponseEntity.ok()

                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + lec.get("filename") + "\"")
                .body(new ByteArrayResource(lecture));
    }

    @GetMapping("/downloadTute/{id}")
    public ResponseEntity<ByteArrayResource> downloadTute(@PathVariable String id) throws IOException {
        byte[] tutorial = classroomService.downloadTute(id);

        //get filename and content type
        HashMap<String, String> tute = classroomService.getDetailsOfTute(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(tute.get("contentType")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + tute.get("filename") + "\"")
                .body(new ByteArrayResource(tutorial));
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String id) throws IOException {
         byte[] image = classroomService.downloadImage(id);

        //get filename and content type
        HashMap<String, String> imgData = classroomService.getDetailsOfImage(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(imgData.get("contentType")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imgData.get("filename") + "\"")
                .body(new ByteArrayResource(image));
    }

    //update all
    @PutMapping("/updatewithFile")
    public ResponseEntity<?> updateClassroomWithFile(@RequestParam("id") String id, @RequestParam("grade") String grade, @RequestParam("subject") String subject, @RequestParam("topic") String topic,
                                                     @RequestParam("description") String description, @RequestParam("date") String date, @RequestParam("time") String time, @RequestParam("link") String link,
                                                     @RequestParam("addedBy") String addedBy, @RequestParam("lecFile") MultipartFile lecFile, @RequestParam("tuteFile") MultipartFile tuteFile, @RequestParam("classImg") MultipartFile classImg) throws IOException {

        String res = classroomService.updateWithLec(id, grade, subject, topic, description, date,time,link,addedBy,lecFile,tuteFile,classImg);
        return ResponseEntity.ok(res);
    }

    //update without files
    @PutMapping("/updatewithoutFiles")
    public ResponseEntity<?> updateWithoutFiles(@RequestParam("id") String id, @RequestParam("grade") String grade, @RequestParam("subject") String subject, @RequestParam("topic") String topic,
                                                @RequestParam("description") String description, @RequestParam("date") String date, @RequestParam("time") String time, @RequestParam("link") String link,
                                                @RequestParam("addedBy") String addedBy) {
        String res = classroomService.updateWithoutFiles(id, grade, subject, topic, description, date,time,link,addedBy);
        return ResponseEntity.ok(res);
    }
}
