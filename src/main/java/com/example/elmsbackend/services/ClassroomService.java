package com.example.elmsbackend.services;

import com.example.elmsbackend.model.Classroom;
import com.example.elmsbackend.repository.ClassroomRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    private final GridFsTemplate gridFsTemplate;

    private final GridFsOperations operations;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository, GridFsTemplate gridFsTemplate, GridFsOperations operations) {
        this.classroomRepository = classroomRepository;
        this.gridFsTemplate = gridFsTemplate;
        this.operations = operations;
    }

    public Classroom addClassroom(String grade, String subject, String topic, String description, String date, String time, String link, String addedBy,
                                  MultipartFile lecFile, MultipartFile tuteFile, MultipartFile classImg) throws IOException {

        /*
         * Store the file in the Gridfs and return its object ID
         */
        Object fileId = gridFsTemplate.store(lecFile.getInputStream(),lecFile.getOriginalFilename(), lecFile.getContentType());

        Object fileId1 = gridFsTemplate.store(tuteFile.getInputStream(),tuteFile.getOriginalFilename(), tuteFile.getContentType());

        Object fileId2 = gridFsTemplate.store(classImg.getInputStream(),classImg.getOriginalFilename(), classImg.getContentType());
        /*
         * Create a new library object and set data
         * FileID - the object Id of the file
         * FileName - original name of the file
         * then save it in the db
         * and return the list to the frontend
         */
        Classroom classroom = new Classroom();
        classroom.setGrade(grade);
        classroom.setSubject(subject);
        classroom.setTopic(topic);
        classroom.setDescription(description);
        classroom.setDate(date);
        classroom.setTime(time);
        classroom.setLink(link);
        classroom.setAddedBy(addedBy);

        classroom.setLec_fileId(fileId.toString());
        classroom.setLec_filename(lecFile.getOriginalFilename());

        classroom.setTute_fileId(fileId1.toString());
        classroom.setTute_filename(tuteFile.getOriginalFilename());

        classroom.setImg_fileId(fileId2.toString());
        classroom.setImg_filename(classImg.getOriginalFilename());

        classroomRepository.save(classroom);

        return classroom;

    }

    public Classroom updateClassroom(String id, String grade, String subject, String topic, String description, String date, String time, String link, String addedBy,
                                     MultipartFile lecFile, MultipartFile tuteFile, MultipartFile classImg) throws IOException {

        /*
         * Find the existing record from the db
         */
        Classroom updatedClassroom = classroomRepository.findClassroomById(id);
        String deletedLecId;
        String deletedTuteId;
        String deletedImgId;

        /*
         * If record exist, get the fileID and delete the file
         * from the GridFs
         */
        if ( updatedClassroom != null) {
            deletedLecId = deleteLec(updatedClassroom.getLec_fileId());
            deletedTuteId = deleteTute(updatedClassroom.getTute_fileId());
            deletedImgId = deleteImg(updatedClassroom.getImg_fileId());

            /*
             * If the file was deleted then upload the new file
             * and update the necessary data in the database
             */
            if ( deletedLecId != null || deletedTuteId != null || deletedImgId != null) {

                Object fileId = gridFsTemplate.store(lecFile.getInputStream(),lecFile.getOriginalFilename(), lecFile.getContentType());

                Object fileId1 = gridFsTemplate.store(tuteFile.getInputStream(),tuteFile.getOriginalFilename(), tuteFile.getContentType());

                Object fileId2 = gridFsTemplate.store(classImg.getInputStream(),classImg.getOriginalFilename(), classImg.getContentType());

                updatedClassroom.setGrade(grade);
                updatedClassroom.setSubject(subject);
                updatedClassroom.setTopic(topic);
                updatedClassroom.setDescription(description);
                updatedClassroom.setDate(date);
                updatedClassroom.setTime(time);
                updatedClassroom.setLink(link);
                updatedClassroom.setAddedBy(addedBy);

                updatedClassroom.setLec_fileId(fileId.toString());
                updatedClassroom.setLec_filename(lecFile.getOriginalFilename());

                updatedClassroom.setTute_fileId(fileId1.toString());
                updatedClassroom.setTute_filename(tuteFile.getOriginalFilename());

                updatedClassroom.setImg_fileId(fileId2.toString());
                updatedClassroom.setImg_filename(classImg.getOriginalFilename());

                classroomRepository.save(updatedClassroom);


            }

        }

        return updatedClassroom;

    }

    // delete file from GridFs
    public String deleteLec(String fileId) {

        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(fileId)));
        return fileId;
    }

    public String deleteTute(String fileId1) {

        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(fileId1)));
        return fileId1;
    }

    public String deleteImg(String fileId2) {

        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(fileId2)));
        return fileId2;
    }

    public String deleteClassroom(String id) {
        /*
         * Find the existing record from the db
         */
        Classroom classroom = classroomRepository.findClassroomById(id);

        /*
         * If record exist, get the fileID and delete the file
         * from the GridFs
         */
        if ( classroom != null) {
            String deleteLec = deleteLec(classroom.getLec_fileId());
            String deleteTute = deleteTute(classroom.getTute_fileId());
            String deleteImg = deleteImg(classroom.getImg_fileId());


            /*
             * If the file was deleted
             * then delete the record from the db
             */
            if ( deleteLec != null && deleteTute != null && deleteImg != null) {
                classroomRepository.deleteById(id);
            }
        }

        return id;

    }

    public List<Classroom> getClassroomByGrade(String status) {
        return classroomRepository.findByGrade(status);
    }

    public List<Classroom> getClassroomBySubject(String subject) {
        return classroomRepository.findBySubject(subject);
    }

    public List<Classroom> getClassroomByTopic(String topic) {
        return classroomRepository.findByTopic(topic);
    }


    public Optional<Classroom> getClassroomById(String id){
        return classroomRepository.findById(id);
    }

    //download template file
    public byte[] downloadLecture(String lec_fileId) throws IOException {

        //find file from DB
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(lec_fileId)));

        //setting data to byte array
        byte[] file = new byte[0];

        if (gridFSFile != null) {
            file = IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream());
        }

        return file;
    }

    //sending filename and content type through hashmap <- part of DOWNLOAD process
    public HashMap<String, String> getDetailsOfLecture(String id) {
        //find file from DB
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        HashMap<String, String> lecture = new HashMap<>();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            lecture.put("filename", gridFSFile.getFilename());
        }

        return lecture;
    }

    public byte[] downloadTute(String tute_fileId) throws IOException {

        //find file from DB
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(tute_fileId)));

        //setting data to byte array
        byte[] file = new byte[0];

        if (gridFSFile != null) {
            file = IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream());
        }

        return file;
    }

    //sending filename and content type through hashmap <- part of DOWNLOAD process
    public HashMap<String, String> getDetailsOfTute(String id) {
        //find file from DB
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        HashMap<String, String> tute = new HashMap<>();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            tute.put("contentType", gridFSFile.getMetadata().get("_contentType").toString());
            tute.put("filename", gridFSFile.getFilename());
        }

        return tute;
    }

    public byte[] downloadImage(String img_fileId) throws IOException {

        //find file from DB
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(img_fileId)));

        //setting data to byte array
        byte[] file = new byte[0];

        if (gridFSFile != null) {
            file = IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream());
        }

        return file;
    }

    public HashMap<String, String> getDetailsOfImage(String id) {
        //find file from DB
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        HashMap<String, String> image = new HashMap<>();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            image.put("contentType", gridFSFile.getMetadata().get("_contentType").toString());
            image.put("filename", gridFSFile.getFilename());
        }

        return image;
    }

    public String updateWithLec(String id,String grade, String subject, String topic, String description, String date, String time, String link, String addedBy,
                                MultipartFile lecFile, MultipartFile tuteFile, MultipartFile classImg) throws IOException {
        //find and delete existing file from gridFs
        Classroom classroom = classroomRepository.findClassroomById(id);
        String LecFileId = deleteTemplate(classroom.getLec_fileId());
        String TutteFileId = deleteTemplate(classroom.getTute_fileId());
        String imgFileId = deleteTemplate(classroom.getImg_fileId());

//-----------------------------------------------------------------------------------------------------------------------------------------

        if (LecFileId != null && imgFileId != null && TutteFileId != null) {
//            if file is deleted then insert new file
            List<String> idList = (List<String>) addClassroom(grade,subject,topic,description,date,time,link,addedBy,lecFile,tuteFile,classImg);

            classroom.setGrade(grade);
            classroom.setSubject(subject);
            classroom.setTopic(topic);
            classroom.setDescription(description);
            classroom.setDate(date);
            classroom.setTime(time);
            classroom.setLink(link);
            classroom.setAddedBy(addedBy);
            classroom.setLec_fileId(idList.get(0));
            classroom.setLec_filename(lecFile.getOriginalFilename());
            classroom.setTute_fileId(idList.get(1));
            classroom.setTute_filename(tuteFile.getOriginalFilename());
            classroom.setImg_fileId(idList.get(1));
            classroom.setImg_filename(classImg.getOriginalFilename());

            classroomRepository.save(classroom);
        }

        return id;
    }

    public String deleteTemplate(String id) {

        //delete File from DB
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
        return id;
    }

    //update without files
    public String updateWithoutFiles(String id,String grade, String subject, String topic, String description, String date, String time, String link, String addedBy) {
        //find query
        Classroom classroom = classroomRepository.findClassroomById(id);
        classroom.setGrade(grade);
        classroom.setSubject(subject);
        classroom.setTopic(topic);
        classroom.setDescription(description);
        classroom.setDate(date);
        classroom.setTime(time);
        classroom.setLink(link);
        classroom.setAddedBy(addedBy);

        classroomRepository.save(classroom);

        return id;
    }


}
