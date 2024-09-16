package com.example.minor_1.services;

import com.example.minor_1.models.Admin;
import com.example.minor_1.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepo adminRepo;

    public void create(Admin admin){
        adminRepo.save(admin);
    }

    public Admin find(Integer adminId){
        return adminRepo.findById(adminId).orElse(null);
    }
}
