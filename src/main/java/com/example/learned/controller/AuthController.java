package com.example.learned.controller;

import com.example.learned.dao.UserRepository;
import com.example.learned.dao.UsersRolesRepository;
import com.example.learned.mapper.UserMapper;
import com.example.learned.mapper.UserRoleMapper;
import com.example.learned.model.DataResult;
import com.example.learned.model.auth.AuthRequestDto;
import com.example.learned.model.auth.AuthenticationDto;
import com.example.learned.model.auth.UserRegisterRequestDto;
import com.example.learned.model.request.RefreshRequestDto;
import com.example.learned.service.authservice.AuthService;
import com.example.learned.service.authservice.JwtService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/v1/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UsersRolesRepository usersRolesRepository;
    private final UserRoleMapper userRoleMapper;
    private final UserMapper userMapper;
    @PostMapping(value = "/register",consumes = "application/json", produces = "application/json")
    public DataResult<AuthenticationDto> register(
            @RequestBody UserRegisterRequestDto requestDto
    ) throws MessagingException, UnsupportedEncodingException {
        return authService.register(requestDto);
    }

    @PostMapping(value = "/login",consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationDto> login(
            @RequestBody AuthRequestDto authRequestDto
    ) {
        return ResponseEntity.ok(authService.authenticate(authRequestDto));
    }
    @PostMapping(value = "/admin/register",consumes = "application/json", produces = "application/json")
    public DataResult<AuthenticationDto> registerAdmin(
            @RequestBody UserRegisterRequestDto requestDto
    )  {
        return authService.registerAdmin(requestDto);
    }

    @PostMapping(value = "/admin/login",consumes = "application/json", produces = "application/json")
    public ResponseEntity<AuthenticationDto> loginAdmin(
            @RequestBody AuthRequestDto authRequestDto
    ) {
        return ResponseEntity.ok(authService.authenticate(authRequestDto));
    }
    @PostMapping(value = "/generateAccessToken",consumes = "application/json", produces = "application/json")
    public AuthenticationDto generateAccessToken(@RequestBody RefreshRequestDto refreshRequestDto){
        return authService.generateAccessToken(refreshRequestDto.getRefreshToken());
    }
    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void deleteUser(@PathVariable Long userId){
        authService.deleteUser(userId);
    }



}
