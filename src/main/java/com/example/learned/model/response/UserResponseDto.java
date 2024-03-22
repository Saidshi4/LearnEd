package com.example.learned.model.response;

import com.example.learned.entity.enums.EUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private Integer age;
    private String phoneNumber;
    private String email;
    private EUserStatus status;
    private String imageData;


}
