package com.example.elmsbackend.controller;

import com.example.elmsbackend.model.Notice;
import com.example.elmsbackend.model.Subject;
import com.example.elmsbackend.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/Subject")
public class SubjectController {

    @Autowired
    public final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addSubjects(@RequestBody Subject subject){
        try {
            subjectService.addSubjects(subject);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getSubjects(){
        try {
            List<Subject> subjects = subjectService.getAllSubjects();
            return new ResponseEntity<>(subjects, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
