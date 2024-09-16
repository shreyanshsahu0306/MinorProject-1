package com.example.minor_1.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String txnId;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @CreationTimestamp
    private Date createdOn;  //T1

    @UpdateTimestamp
    private Date updateOn;  //T2

    private Integer fine;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"transactionList"})
    private Book book;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"transactionList"})
    private Student student;

    @JoinColumn
    @ManyToOne
    @JsonIgnoreProperties({"transactionList"})
    private Admin admin;
}
