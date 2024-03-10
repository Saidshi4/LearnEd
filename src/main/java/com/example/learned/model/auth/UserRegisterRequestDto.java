package com.example.learned.model.auth;

import com.example.learned.entity.enums.EUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String email;
    private EUserStatus status;
    private String password;
}
