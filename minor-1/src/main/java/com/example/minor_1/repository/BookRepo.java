package com.example.minor_1.repository;

import com.example.minor_1.models.Book;
import com.example.minor_1.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Integer> {


    List<Book> findByGenre(Genre genre);


    @Query("SELECT b from Book b, Author a where b.my_author.id = a.id and a.name = ?1")
    List<Book> findByAuthor_Name(String authorName);

    List<Book> findByName(String name);
}
