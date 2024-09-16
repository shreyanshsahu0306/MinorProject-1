package com.example.minor_1.repository;

import com.example.minor_1.models.Book;
import com.example.minor_1.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {



}
