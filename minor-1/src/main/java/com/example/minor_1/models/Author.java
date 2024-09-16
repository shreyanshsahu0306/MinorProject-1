package com.example.minor_1.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @CreationTimestamp
    private Date createdOn;

    @OneToMany(mappedBy = "my_author") //(Author : Book :: 1 : N)
    // mapped by meaning is - while quering for author also look for book using attribute my_author
    //if we don't use @one to many here then also it's fine as we will just get the author details only
    private List<Book> bookList; // BACK REFERENCE.. To get the list of books from Author repo


}
