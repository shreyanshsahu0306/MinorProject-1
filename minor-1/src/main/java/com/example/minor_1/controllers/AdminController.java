package com.example.minor_1.controllers;


import com.example.minor_1.dtos.CreateAdminRequest;
import com.example.minor_1.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/admin")
    public void createAdmin(@RequestBody @Valid CreateAdminRequest car){
        adminService.create(car.to());

    }
}
