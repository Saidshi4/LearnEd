package com.example.learned.model.response;

import com.example.learned.entity.enums.EUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVerifyResponseDto {
    private Long id;
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
    private Boolean educationalInstitution;
    private Boolean masterDegree;
    private Boolean educationStatus;
    private String imageUrl;

}
