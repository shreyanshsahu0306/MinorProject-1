package com.example.minor_1.dtos;


import com.example.minor_1.models.Admin;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;


    public Admin to(){
       return Admin.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}
