package com.example.minor_1.services;
import com.example.minor_1.models.*;
import com.example.minor_1.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepo bookRepo;

    public void createOrUpdate(Book book){

        Author authorObj = book.getMy_author(); //creating author through author service
        Author savedAuthor = authorService.getOrCreate(authorObj); // getting the author created  from db

        book.setMy_author(savedAuthor); // setting the author object to book
        bookRepo.save(book); // persisting the book entry which has author mapped to it.
    }

    public List<Book> find(String searchKey, String searchValue)  throws Exception{

         switch (searchKey) {
             case "id" :  {
                Optional<Book> book = bookRepo.findById(Integer.parseInt(searchValue));
                if(book.isPresent()){
                    return Arrays.asList((book.get()));
                }
                else{
                    return new ArrayList<>();
                }
            }
             case "genre" : return bookRepo.findByGenre(Genre.valueOf(searchValue));
             case "author_name" : return bookRepo.findByAuthor_Name(searchValue);
             case "name" : return bookRepo.findByName(searchValue);
             default : throw new Exception("Search Key is not valid " + searchValue);
        }

    }
}
