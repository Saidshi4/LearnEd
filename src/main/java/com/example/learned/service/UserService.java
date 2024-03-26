package com.example.learned.service;

import com.example.learned.dao.UserRepository;
import com.example.learned.entity.UserEntity;
import com.example.learned.exception.AlreadyExistsException;
import com.example.learned.mapper.UserMapper;
//import com.example.learned.model.response.PendingUserResponseDto;
import com.example.learned.model.request.ChangePasswordDto;
import com.example.learned.model.request.UpdateUserDto;
import com.example.learned.model.response.UserResponseDto;
import com.example.learned.model.response.UserVerifyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public UserResponseDto getUserById(Long userId){
        return userMapper.mapEntityToResponseDto(userRepository.findById(userId).orElseThrow());
    }
    public void changePassword(Long userId, ChangePasswordDto changePasswordDto) throws RuntimeException{
        UserEntity user=userRepository.findById(userId).orElseThrow();
        if(passwordEncoder.matches(changePasswordDto.getCurrentPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
            userRepository.save(user);
        }else{
            throw new RuntimeException("Current password invalid");
        }
    }
    public UserResponseDto updateUser(Long userId, UpdateUserDto updateUserDto)throws AlreadyExistsException{
        UserEntity user = userRepository.findById(userId).orElseThrow();
        if (!user.getNickname().matches(updateUserDto.getNickname())){
            UserEntity userByNickname = userRepository.findByNickname(updateUserDto.getNickname());
            if (userByNickname == null){
                user.setFirstName(updateUserDto.getFirstName());
                user.setLastName(updateUserDto.getLastName());
                user.setNickname(updateUserDto.getNickname());
                user.setPhoneNumber(updateUserDto.getPhoneNumber());
            }
            else throw new AlreadyExistsException("Nickname already exists");
        }else {
            user.setFirstName(updateUserDto.getFirstName());
            user.setLastName(updateUserDto.getLastName());
            user.setPhoneNumber(updateUserDto.getPhoneNumber());
        }
        return userMapper.mapEntityToResponseDto(user);
    }
}
