package com.example.elmsbackend.services;

import com.example.elmsbackend.model.User;
import com.example.elmsbackend.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AdminUserService(AdminUserRepository adminUserRepository, MongoTemplate mongoTemplate) {
        this.adminUserRepository = adminUserRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void addAdminUser(User user){
        adminUserRepository.insert(user);
    }

    public List<User> getAllAdminUsers(){
        List <User> users = adminUserRepository.findAll();
        return users;
    }

    public void deleteAdminUser(String id){
        adminUserRepository.deleteById(id);
    }

    public List<User> filterUserByRole(String role) {
        return mongoTemplate.find(Query.query(Criteria.where("role").is(role)), User.class);
    }

    public void updateUser(User user){
        adminUserRepository.save(user);
    }


    public Optional<User> getAdminByUsername(String indexno){
        return adminUserRepository.findById(indexno);
    }

}
