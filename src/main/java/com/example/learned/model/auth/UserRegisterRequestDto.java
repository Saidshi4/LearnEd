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
    private String fatherName;
    private LocalDate birthDate;
    private String serialNumber;
    private String finCode;
    private String cityOrDistrict;
    private String email;
    private String phoneNumber;
    private EUserStatus status;
    private String password;
    private Boolean educationalInstitution;
    private Boolean masterDegree;
    private Boolean educationStatus;

}
