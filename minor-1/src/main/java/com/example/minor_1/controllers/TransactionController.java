package com.example.minor_1.controllers;


import com.example.minor_1.dtos.CreateTransactionRequest;
import com.example.minor_1.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public String initiateTxn(@RequestBody @Valid CreateTransactionRequest ctr) throws Exception {

        //for request body we need:
        //student id,
        //book id;
        //admin id,
        //transaction type - issue or return

        return transactionService.initiateTxn(ctr);
    }
}
