package com.example.minor_1.dtos;

import com.example.minor_1.models.Student;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private Integer age;

    public Student to(){

        return Student.builder()
                .name(this.name)
                .email(this.email)
                .age(this.age)
                .build();
    }


}
