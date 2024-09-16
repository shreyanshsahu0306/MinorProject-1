package com.example.minor_1.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;


    @ManyToOne //(Book : Author :: N : 1)
    @JoinColumn //creates a foreign key column of author table in book table
    @JsonIgnoreProperties({"bookList"}) ///print the author details but ignore the booklist or else an infinite loop will be created
    private Author my_author;

    @ManyToOne //(Book : Student :: N : 1)
    @JoinColumn //creates a foreign key column of student table in book table
    @JsonIgnoreProperties({"bookList"}) ///print the student details but ignore the booklist or else an infite loop will be created
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;


}
