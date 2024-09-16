package com.example.minor_1.services;

import com.example.minor_1.dtos.CreateTransactionRequest;
import com.example.minor_1.models.*;
import com.example.minor_1.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    StudentService studentService;

    @Autowired
    AdminService adminService;

    @Autowired
    BookService bookService;

    @Value("${student.allowed.max-books}") // from application.properties
    Integer maxBooksAllowed;

    @Value("${student.allowed.duration}") // from application.properties
    Integer duration;

    @Autowired
    TransactionRepo transactionRepo;

    public String initiateTxn(CreateTransactionRequest request) throws Exception {

        /* *Issuance
                1. check if book is available or not and student is valid or not.
                2. entry in the Txn table
                3. check if student has reached max limit of books issued.
                4. book to be assigned to student -> update in book table.

         */

        /* *Return
                1. check if book is valid or not and student is valid or not.
                2. entry in the Txn table
                3. due date check and fine calculation.
                4. if no fine, then de-allocate the book from student's name ->  update in book table.

         */
        return request.getTransactionType() == TransactionType.ISSUE ? issuance(request) : returnBook(request);

    }

    public String issuance(CreateTransactionRequest request) throws Exception {

        //1. check if book is available or not and student is valid or not.
        Student student = studentService.find(request.getStudentId());
        Admin admin = adminService.find(request.getAdminId());
        List<Book> bookList = bookService.find("id", String.valueOf(request.getBookId()));

        Book book = bookList!= null && bookList.size() > 0 ? bookList.get(0) : null;

        if(student == null
                || admin == null
                || book == null
                || book.getStudent()!= null
                || student.getBookList().size() >= maxBooksAllowed){
            throw new Exception("Invalid Request for book issuance");

        }
        //2. entry in the Txn table
        Transaction transaction = null;
        try {
             transaction = Transaction.builder()
                    .txnId(UUID.randomUUID().toString())
                    .student(student)
                    .book(book)
                    .admin(admin)
                    .transactionType(request.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING)
                    .build();

            transactionRepo.save(transaction);

            //4./ update in book table.
            book.setStudent(student);
            bookService.createOrUpdate(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        }
        catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
        }
        finally{
            transactionRepo.save(transaction);
        }

        return transaction.getTxnId();
    }

    public String returnBook(CreateTransactionRequest request) throws Exception {

        //1.  check if book is valid or not and student is valid or not.
        Student student = studentService.find(request.getStudentId());
        Admin admin = adminService.find(request.getAdminId());
        List<Book> bookList = bookService.find("id", String.valueOf(request.getBookId()));

        Book book = bookList != null && bookList.size() > 0 ? bookList.get(0) : null;

        if (student == null
                || admin == null
                || book == null
                || book.getStudent() == null
                || book.getStudent().getId() != student.getId()) {
            throw new Exception("Invalid Request for book return");

        }
        //fine calculation - need to get the corresponding issuance transaction
        Transaction txn = transactionRepo.findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(student, book, TransactionType.ISSUE);
        if (txn == null) {
            throw new Exception("Invalid request while getting corresponding issuance transaction");
        }

        //2. entry in the Txn table
        Transaction transaction = null;
        try {
            Integer fineTotal = calculateFine(txn.getCreatedOn(), duration);
            transaction = Transaction.builder()
                    .txnId(UUID.randomUUID().toString())
                    .student(student)
                    .book(book)
                    .admin(admin)
                    .transactionType(request.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING)
                    .fine(fineTotal)
                    .build();

            transactionRepo.save(transaction);
            if (fineTotal == 0) {
                book.setStudent(null);
                bookService.createOrUpdate(book);
            }

            //4./ update in book table.
            book.setStudent(student);
            bookService.createOrUpdate(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
        } finally {
            transactionRepo.save(transaction);
        }

        return transaction.getTxnId();
    }

    private static int calculateFine(Date issuanceTime, Integer duration){

        long issueTimeInMiliSec = issuanceTime.getTime();
        long currrentTimeInMiliSec = System.currentTimeMillis();

        long diff = currrentTimeInMiliSec - issueTimeInMiliSec;
        long daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if(daysPassed > duration){
            return (int)(daysPassed - duration) * 5;
        }
        return 0;
    }
}
