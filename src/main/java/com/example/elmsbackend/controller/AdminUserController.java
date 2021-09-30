package com.example.elmsbackend.controller;

import com.example.elmsbackend.model.User;
import com.example.elmsbackend.repository.AdminUserRepository;
import com.example.elmsbackend.services.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/adminuser")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Autowired
    private AdminUserRepository adminUserRepository;


    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping("/addadminuser")
    public ResponseEntity<User> addAdminUser(@RequestBody User user){
        adminUserService.addAdminUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
            adminUserService.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/alladmin")
    public ResponseEntity<List<User>> getAllAdminUsers(){
        return ResponseEntity.ok(adminUserService.getAllAdminUsers());
    }

    @GetMapping("/getadminuser/{indexno}")
    public Object getAdminUser(@PathVariable String indexno){
        return ResponseEntity.ok(adminUserService.getAdminByUsername(indexno));

    }

    @GetMapping("/getuser/{role}")
    public ResponseEntity<List<User>> filterUserByRole(@PathVariable String role) {
        return new ResponseEntity<>(adminUserService.filterUserByRole(role), HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser/{id}")
    public void deleteAdminUser(@PathVariable String id){
        adminUserService.deleteAdminUser(id);
    }
}
