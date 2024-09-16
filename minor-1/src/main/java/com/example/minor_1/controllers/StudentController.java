package com.example.minor_1.controllers;


import com.example.minor_1.dtos.CreateStudentRequest;
import com.example.minor_1.models.Student;
import com.example.minor_1.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

   @Autowired
    StudentService studentService;

    @PostMapping("/createStudent")
    public void createStudent(@RequestBody @Valid CreateStudentRequest csr){
        studentService.create(csr.to());
    }

    @GetMapping("/getStudent")
    public Student findStudent(@RequestParam("id") int studentId){
        return studentService.find(studentId);
    }
}
