package com.example.elmsbackend.controller;

import com.example.elmsbackend.model.Notice;
import com.example.elmsbackend.services.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/Notice")
public class NoticeController {

    @Autowired
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addNotice(@RequestBody Notice notice){
        try {
            noticeService.addNotice(notice);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getNotices(){
        try {
            List<Notice> notices = noticeService.getAllNotices();
            return new ResponseEntity<>(notices, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotice(@PathVariable String id){
        try{
            return new ResponseEntity<>(noticeService.getNoticeById(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/grade/{grade}")
    public ResponseEntity<?> getNoticesByGrade(@PathVariable String grade){
        try {
            return new ResponseEntity<>(noticeService.getNoticesByGrade(grade), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/subject/{subject}")
    public ResponseEntity<?> getNoticesBySubject(@PathVariable String subject){
        try {
            return new ResponseEntity<>(noticeService.getNoticesBySubject(subject), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNotice(@PathVariable String id){
        try {
            noticeService.deleteNotice(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateNotice(@RequestBody Notice notice){
        try {
            noticeService.updateNotice(notice);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
