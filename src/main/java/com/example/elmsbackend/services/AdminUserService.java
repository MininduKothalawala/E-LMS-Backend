package com.example.elmsbackend.services;


import com.example.elmsbackend.model.AdminUser;
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

    public void addAdminUser(AdminUser adminUser){
        adminUserRepository.insert(adminUser);
    }

    public List<AdminUser> getAllAdminUsers(){
        List <AdminUser> adminUsers = adminUserRepository.findAll();
        System.out.println("admin user from db ::"+adminUsers);
        return adminUsers;
    }

    public void deleteAdminUser(String id){
        adminUserRepository.deleteById(id);
    }

    public Optional<AdminUser> getAdminByUsername(String username){
        return adminUserRepository.findById(username);
    }

}
