package com.example.learned.model.auth;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDto {

    private String accessToken;
    private String refreshToken;
}
