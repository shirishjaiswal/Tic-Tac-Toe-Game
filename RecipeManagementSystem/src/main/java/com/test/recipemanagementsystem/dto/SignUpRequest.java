package com.test.recipemanagementsystem.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
}
