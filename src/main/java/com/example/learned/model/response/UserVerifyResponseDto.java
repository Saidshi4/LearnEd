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
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String email;
    private EUserStatus status;
    private String imageUrl;
}
