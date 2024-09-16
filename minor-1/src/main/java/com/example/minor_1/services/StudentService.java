package com.example.minor_1.services;


import com.example.minor_1.models.Student;
import com.example.minor_1.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public void create(Student student){

        studentRepo.save(student);
    }

    public Student find(int studentId) {

        return studentRepo.findById(studentId).orElse(null);
    }
}
