package com.example.elmsbackend.controller;

import com.example.elmsbackend.services.DasboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private final DasboardService dasboardService;

    public DashboardController(DasboardService dasboardService) {
        this.dasboardService = dasboardService;
    }

    @GetMapping("/classes")
    public ResponseEntity<?> getClassCount(){
        try {
            return new ResponseEntity<>(dasboardService.getTodayClassCount(), HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/notices")
    public ResponseEntity<?> getNoticeCount(){
        try {
            return new ResponseEntity<>(dasboardService.getNewNoticesCount(), HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/grades")
    public ResponseEntity<?> getGradeCount(){
        try {
            return new ResponseEntity<>(dasboardService.getGradeCount(), HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/resources")
    public ResponseEntity<?> getResourceTypes(){
        try {
            return new ResponseEntity<>(dasboardService.getResourceTypes(), HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUserTypes(){
        try {
            return new ResponseEntity<>(dasboardService.getUserTypes(), HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/subjects")
    public ResponseEntity<?> getSubjectCount(){
        try {
            return new ResponseEntity<>(dasboardService.getSubjectCount(), HttpStatus.OK) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
