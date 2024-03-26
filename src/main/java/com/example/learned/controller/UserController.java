package com.example.learned.controller;

import com.example.learned.exception.AlreadyExistsException;
import com.example.learned.model.DataResult;
import com.example.learned.model.request.ChangePasswordDto;
import com.example.learned.model.request.UpdateUserDto;
import com.example.learned.model.response.UserResponseDto;
import com.example.learned.model.response.UserVerifyResponseDto;
import com.example.learned.service.UserService;
import com.example.learned.service.authservice.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/getUser")
    public ResponseEntity<DataResult<UserResponseDto>> getUserById(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Invalid token");
            }
            Long userId = jwtService.extractUserIdFromAccessToken(token.replace("Bearer ", ""), true);
            UserResponseDto userResponseDto = userService.getUserById(userId);
            return ResponseEntity.ok(new DataResult<>("Successfully", HttpStatus.OK.value(), userResponseDto));
        } catch (NoSuchElementException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataResult<>("User not found", HttpStatus.NOT_FOUND.value(), null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResult<>("Invalid token", HttpStatus.BAD_REQUEST.value(), null));
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity<DataResult<UserResponseDto>> updateUser(HttpServletRequest request, @RequestBody UpdateUserDto updateUserDto){
        try {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Invalid token");
            }
            Long userId = jwtService.extractUserIdFromAccessToken(token.replace("Bearer ", ""), true);
            UserResponseDto userResponseDto = userService.updateUser(userId, updateUserDto);
            return ResponseEntity.ok(new DataResult<>("Update user successfully", HttpStatus.OK.value(), userResponseDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResult<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResult<>("Invalid token", HttpStatus.BAD_REQUEST.value(), null));
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<DataResult<?>> changePassword(HttpServletRequest request, @RequestBody ChangePasswordDto changePasswordDto){
        try {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Invalid token");
            }
            Long userId = jwtService.extractUserIdFromAccessToken(token.replace("Bearer ", ""), true);
            userService.changePassword(userId, changePasswordDto);
            return ResponseEntity.ok(new DataResult<>("Update password successfully", HttpStatus.OK.value(), null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResult<>("Invalid token", HttpStatus.BAD_REQUEST.value(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DataResult<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
        }
    }
}





