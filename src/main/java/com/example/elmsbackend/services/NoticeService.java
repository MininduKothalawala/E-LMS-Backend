package com.example.elmsbackend.services;

import com.example.elmsbackend.model.Notice;
import com.example.elmsbackend.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    public final NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    //insert new notice to the system
    public void addNotice(Notice notice){
        noticeRepository.insert(notice);
    }

    //get all notices from system
    public List<Notice> getAllNotices(){
        return noticeRepository.findAll();
    }

    //get notice searching by the id
    public Object getNoticeById(String id){
        return noticeRepository.findById(id);
    }

    //delete notice
    public void deleteNotice(String id){
        noticeRepository.deleteById(id);
    }
}