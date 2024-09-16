package com.example.minor_1.dtos;


import com.example.minor_1.models.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTransactionRequest {

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer bookId;

    @NotNull
    private Integer adminId;

    @NotNull
    private TransactionType transactionType;

}
