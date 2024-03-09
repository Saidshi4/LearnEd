package com.example.learned.service;

import com.example.learned.dao.UserRepository;
import com.example.learned.mapper.UserMapper;
//import com.example.learned.model.response.PendingUserResponseDto;
import com.example.learned.model.response.UserResponseDto;
import com.example.learned.model.response.UserVerifyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long userId){
        return userMapper.mapEntityToResponseDto(userRepository.findById(userId).orElseThrow());
    }
//    public UserVerifyResponseDto getUserAndSubUsers(Long userId){
//        return userMapper.mapEntityToVerifyDto(userRepository.findById(userId).orElse(null));
//    }
//    public List<PendingUserResponseDto> getPendingUsers(){
//        return userMapper.mapEntityToPendingDtos(userRepository.getVerifyUsers());
//    }
}
