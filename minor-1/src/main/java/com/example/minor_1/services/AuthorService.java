package com.example.minor_1.services;
import com.example.minor_1.models.Author;
import com.example.minor_1.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepo authorRepo;

    public Author getOrCreate(Author authorObj) {
        Author retrievedAuthor = authorRepo.findByEmail(authorObj.getEmail());

        if (retrievedAuthor == null){
            retrievedAuthor = authorRepo.save(authorObj);
        }
        return retrievedAuthor;
    }
}
