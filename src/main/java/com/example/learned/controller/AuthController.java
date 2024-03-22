package com.example.learned.controller;

import com.example.learned.exception.AlreadyExistsException;
import com.example.learned.exception.NotFoundException;
import com.example.learned.model.DataResult;
import com.example.learned.model.auth.AuthRequestDto;
import com.example.learned.model.auth.AuthenticationDto;
import com.example.learned.model.auth.UserRegisterRequestDto;
import com.example.learned.model.request.RefreshRequestDto;
import com.example.learned.service.authservice.AuthService;
import com.example.learned.service.authservice.JwtService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataResult<AuthenticationDto>> register(
            @RequestBody UserRegisterRequestDto requestDto
    ) {
        try {
            return ResponseEntity.
                    ok(new DataResult<>("User registered successfully", HttpStatus.OK.value(), authService.register(requestDto)));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new DataResult<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new DataResult<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }

    @PostMapping(value = "/admin/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataResult<AuthenticationDto>> registerAdmin(@RequestBody UserRegisterRequestDto requestDto) {
        try {
            return ResponseEntity.
                    ok(new DataResult<>("User registered successfully", HttpStatus.OK.value(), authService.registerAdmin(requestDto)));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).
                    body(new DataResult<>("Email already exists", HttpStatus.FORBIDDEN.value(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new DataResult<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }
    }
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataResult<AuthenticationDto>> login (@RequestBody AuthRequestDto authRequestDto) {
        try {
            AuthenticationDto authenticationDto = authService.authenticate(authRequestDto);
            return ResponseEntity.ok(new DataResult<>("User login successfully", HttpStatus.OK.value(), authenticationDto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new DataResult<>(e.getMessage(), HttpStatus.FORBIDDEN.value(), null));
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<DataResult<?>> deleteUser(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Invalid token");
            }
            Long userId = jwtService.extractUserIdFromAccessToken(token.replace("Bearer ", ""),true);
            authService.deleteUser(userId);

            return ResponseEntity.ok(new DataResult<>("User deleted successfully", HttpStatus.OK.value(), null));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new DataResult<>("Invalid token", HttpStatus.BAD_REQUEST.value(), null));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new DataResult<>("User not found", HttpStatus.NOT_FOUND.value(), null));
        }
    }

    @PostMapping(value = "/generateAccessToken", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataResult<AuthenticationDto>> generateAccessToken(@RequestBody RefreshRequestDto refreshRequestDto) {
        try {
            return ResponseEntity.ok(new DataResult<>("Token generated successfully", HttpStatus.OK.value(), authService.generateAccessToken(refreshRequestDto.getRefreshToken())));
        }catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new DataResult<>("Invalid refresh token", HttpStatus.FORBIDDEN.value(), null));
        }
    }

}
