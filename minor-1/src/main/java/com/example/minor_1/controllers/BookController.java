package com.example.minor_1.controllers;


import com.example.minor_1.dtos.CreateBookRequest;
import com.example.minor_1.models.Book;
import com.example.minor_1.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@RequestBody @Valid CreateBookRequest cbr){
        //first author shall be created as author is the parent table of book table
        //once author is created then book shall be created.
        bookService.createOrUpdate(cbr.to());
    }

    @GetMapping("/getBook")
    public List<Book> getBookDetails(@RequestParam("key") String key, @RequestParam("value") String value) throws Exception {

        return bookService.find(key, value);
        //localhost:8080/getBook?key=author_name&value=Peter
        //or
        //localhost:8080/getBook?key=genre&value=HISTORY
        // ANY PARAMETER CAN BE USED TO SEARCH BOOK DETAILS//


    }
}
