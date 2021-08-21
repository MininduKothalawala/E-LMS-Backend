package com.example.elmsbackend.services;

import com.example.elmsbackend.model.Library;
import com.example.elmsbackend.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;

@Service
public class LibraryService {
    private final GridFsTemplate template;

    private final GridFsOperations operations;

    private final LibraryRepository repository;

    @Autowired
    public LibraryService(GridFsTemplate template, GridFsOperations operations, LibraryRepository repository) {
        this.template = template;
        this.operations = operations;
        this.repository = repository;
    }

    // add resource
    public Library addResource(String resourceType, String grade, String subject, MultipartFile file) throws IOException {

        /*
         * Store the file in the Gridfs and return its object ID
         */
        Object fileId = template.store(file.getInputStream(),file.getOriginalFilename(), file.getContentType());

        /*
         * Create a new library object and set data
         * FileID - the object Id of the file
         * FileName - original name of the file
         * then save it in the db
         * and return the list to the frontend
         */
        Library library = new Library();
        library.setResourceType(resourceType.toUpperCase(Locale.ROOT));
        library.setGrade(grade);
        library.setSubject(subject.toUpperCase(Locale.ROOT));
        library.setFileId(fileId.toString());
        library.setFileName(file.getOriginalFilename());

        repository.save(library);

        return library;

    }

    // update resource
    public Library updateResource(String id, String resourceType, String grade, String subject, MultipartFile file) throws IOException {

        /*
         * Find the existing record from the db
         */
        Library updatedResource = repository.findLibraryById(id);
        String deletedFileId;

        /*
        * If record exist, get the fileID and delete the file
        * from the GridFs
        */
        if ( updatedResource != null) {
            deletedFileId = deleteFile(updatedResource.getFileId());

            /*
             * If the file was deleted then upload the new file
             * and update the necessary data in the database
             */
            if ( deletedFileId != null ) {

                Object fileId = template.store(file.getInputStream(),file.getOriginalFilename(), file.getContentType());

                updatedResource.setResourceType(resourceType.toUpperCase(Locale.ROOT));
                updatedResource.setGrade(grade);
                updatedResource.setSubject(subject.toUpperCase(Locale.ROOT));
                updatedResource.setFileId(fileId.toString());
                updatedResource.setFileName(file.getOriginalFilename());

                repository.save(updatedResource);


            }

        }

        return updatedResource;

    }

    // delete file from GridFs
    public String deleteFile(String fileId) {

        template.delete(Query.query(Criteria.where("_id").is(fileId)));
        return fileId;
    }

    // delete resource
    public String deleteResource(String id) {
        /*
         * Find the existing record from the db
         */
        Library resource = repository.findLibraryById(id);

        /*
         * If record exist, get the fileID and delete the file
         * from the GridFs
         */
        if ( resource != null) {
            String deleteFile = deleteFile(resource.getFileId());

            /*
             * If the file was deleted
             * then delete the record from the db
             */
            if ( deleteFile != null ) {
                repository.deleteById(id);
            }
        }

        return id;

    }

    //download template file
//    public byte[] downloadTemplate(String tempFileID) throws IOException {
//
//        //find file from DB
//        GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(tempFileID)));
//
//        //setting data to byte array
//        byte[] file = new byte[0];
//
//        if (gridFSFile != null) {
//            file = IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream());
//        }
//
//        return file;
//    }

    //TODO: link
//    public HashMap<String, String> getDetailsOfFile(String id) {
//        //find file from DB
//        GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));
//
//        HashMap<String, String> template = new HashMap<>();
//
//        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
//            template.put("contentType", gridFSFile.getMetadata().get("_contentType").toString());
//            template.put("filename", gridFSFile.getFilename());
//        }
//
//        return template;
//    }

}
