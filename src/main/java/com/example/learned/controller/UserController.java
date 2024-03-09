package com.example.learned.controller;

import com.example.learned.model.DataResult;
import com.example.learned.model.response.UserResponseDto;
import com.example.learned.model.response.UserVerifyResponseDto;
import com.example.learned.service.UserService;
import com.example.learned.service.authservice.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/getUser")
    public UserResponseDto getUserById(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid token");
        }
        Long userId = jwtService.extractUserIdFromAccessToken(token.replace("Bearer ", ""),true);
        return userService.getUserById(userId);

    }
//    @GetMapping("/getPendingUsers")
//    public DataResult<List<PendingUserResponseDto>> getPendingUsers(){
//        return new DataResult<>("Succesfuly", HttpStatus.OK.value(), userService.getPendingUsers());
////    }
//    @GetMapping("/getPendingUsers/{userId}")
//    public DataResult<UserVerifyResponseDto> getUserAndSubData(@PathVariable Long userId){
//        return new DataResult<>("Succesfuly", HttpStatus.OK.value(), userService.getUserAndSubUsers(userId));
    }





