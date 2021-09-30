package com.example.elmsbackend.services;

import com.example.elmsbackend.model.*;
import com.example.elmsbackend.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DasboardService {

    private final LibraryRepository libraryRepository;
    private final AdminUserRepository adminUserRepository;
    private final SubjectRepository subjectRepository;
    private final ClassroomRepository classroomRepository;
    private final NoticeRepository noticeRepository;

    public DasboardService(LibraryRepository libraryRepository, AdminUserRepository adminUserRepository, SubjectRepository subjectRepository, ClassroomRepository classroomRepository, NoticeRepository noticeRepository) {
        this.libraryRepository = libraryRepository;
        this.adminUserRepository = adminUserRepository;
        this.subjectRepository = subjectRepository;
        this.classroomRepository = classroomRepository;
        this.noticeRepository = noticeRepository;
    }

    public int getTodayClassCount(){
        List<Classroom> classrooms = classroomRepository.findAll();
        int classroomCount = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate localDate = LocalDate.now();

        for (Classroom classroom : classrooms){
            if(classroom.getDate().equals(dtf.format(localDate))){
                classroomCount = classroomCount + 1;
            }
        }
        return classroomCount;
    }

    public int getNewNoticesCount(){
        List<Notice> notices = noticeRepository.findAll();
        int noticeCount = 0;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate localDate = LocalDate.now();

        for (Notice notice : notices){
            if(notice.getEnteredDate().equals(dtf.format(localDate))){
                noticeCount = noticeCount + 1;
            }
        }

        return noticeCount;
    }

    public int getGradeCount(){
        return (int) subjectRepository.findAll().stream().count();
    }

    //Resource Types array
    public int[] getResourceTypes(){
        List<Library> libraryList = libraryRepository.findAll();
        int syllabus = 0;
        int TeachersGuide = 0;
        int[] respurces = new int[2];

        for (Library library : libraryList){
            if (library.getResourceType().equals("Syllabus")){
                syllabus = syllabus + 1;
            }
            else if (library.getResourceType().equals("Guide")){
                TeachersGuide = TeachersGuide + 1;
            }
        }

        respurces[0] = syllabus;
        respurces[1] = TeachersGuide;

        //syllabus,TeachersGuide
        return respurces;
    }

    //User types array
    public int[] getUserTypes(){
        List<User> users = adminUserRepository.findAll();
        int admin=0;
        int teacher=0;
        int student=0;
        int[] userTypes = new int[3];

        for (User user : users){
            if (user.getRole().equals("admin")){
                admin = admin + 1;
            }
            else if (user.getRole().equals("student")){
                student = student + 1;
            }
            else if (user.getRole().equals("teacher")){
                teacher = teacher + 1;
            }
        }

        userTypes[0] = admin;
        userTypes[1] = teacher;
        userTypes[2] = student;

        //admin, teacher, student
        return userTypes;
    }

    //Subject count array according to grade
    public int[] getSubjectCount(){
        List<Subject> subjects = subjectRepository.findAll();
        int[] subjectCount = new int[13];
        int counter = 0;

        for (Subject subject : subjects){
            subjectCount[counter] = (int) subject.getSubjects().stream().count();
            counter = counter + 1;
        }

        return subjectCount;
    }
}
