package com.example.minor_1.repository;

import com.example.minor_1.models.Book;
import com.example.minor_1.models.Student;
import com.example.minor_1.models.Transaction;
import com.example.minor_1.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    //@Query("select * from transaction where studentId = ?1 and bookId = ?2 and transactionType = ?3 order by id desc limit 1")
    //the below method name will work the sme as the above query
    Transaction findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(Student student, Book book, TransactionType transactionType);
}
