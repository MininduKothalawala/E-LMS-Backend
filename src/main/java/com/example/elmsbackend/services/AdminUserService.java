package com.example.elmsbackend.services;


import com.example.elmsbackend.model.User;
import com.example.elmsbackend.repository.AdminUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    public AdminUserService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    public void addAdminUser(User user){
        adminUserRepository.insert(user);
    }

    public List<User> getAllAdminUsers(){
        List <User> users = adminUserRepository.findAll();
        System.out.println("admin user from db ::"+ users);
        return users;
    }

    public void deleteAdminUser(String id){
        adminUserRepository.deleteById(id);
    }

    public Optional<User> getAdminByUsername(String username){
        return adminUserRepository.findById(username);
    }

}
