package com.example.learned.model;

import com.example.learned.entity.enums.EUserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String email;
    private EUserStatus status;
    private String password;
    private List<UserRoleDto> userRoles;


}
