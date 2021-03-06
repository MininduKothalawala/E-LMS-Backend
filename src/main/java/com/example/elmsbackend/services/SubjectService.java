package com.example.elmsbackend.services;

import com.example.elmsbackend.model.Subject;
import com.example.elmsbackend.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    public final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    //add subjects
    public void addSubjects(Subject subject){
        subjectRepository.insert(subject);
    }

    //get all subjects
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    //get subjects list according to the grade
    public List<String> getSubjectsByGrade(String grade){
        List<Subject> grades = subjectRepository.findAll();
        List<String> subjects = new ArrayList<>();

        for (Subject item: grades){
            if(item.getGrade().equals(grade)){
                subjects = item.getSubjects();
            }
        }
        return subjects;
    }
}
