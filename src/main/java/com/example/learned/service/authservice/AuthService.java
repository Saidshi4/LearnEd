package com.example.learned.service.authservice;

import com.example.learned.dao.RoleRepository;
import com.example.learned.dao.UserRepository;
import com.example.learned.dao.UsersRolesRepository;
import com.example.learned.entity.RoleEntity;
import com.example.learned.entity.UserEntity;
import com.example.learned.entity.UserRoleEntity;
import com.example.learned.entity.enums.ERole;
import com.example.learned.mapper.UserMapper;
import com.example.learned.mapper.UserRoleMapper;
import com.example.learned.model.DataResult;
import com.example.learned.model.UserGetDto;
import com.example.learned.model.auth.AuthRequestDto;
import com.example.learned.model.auth.AuthenticationDto;
import com.example.learned.model.auth.UserRegisterRequestDto;
import com.example.learned.model.request.UserRoleRequestDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRoleMapper userRoleMapper;
    private final UsersRolesRepository usersRolesRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserMapper userMapper;
    private final UserDetailsService userDetailsService;


    @Transactional
    public DataResult<AuthenticationDto> register(UserRegisterRequestDto requestDto) throws MessagingException, UnsupportedEncodingException {

        UserEntity user=userMapper.mapRegisterRequestDtoToEntity(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);
        UserEntity user1 = userRepository.findUserByEmail(requestDto.getEmail()).orElseThrow();
        RoleEntity role=roleRepository.findByName(ERole.USER);
        UserRoleEntity userRole=UserRoleEntity.builder().
                user(user1)
                .role(role)
                .build();
        usersRolesRepository.save(userRole);
        var accessToken = jwtService.generateAccessToken(user1);
        return new DataResult<>("Succesfuly", HttpStatus.OK.value(),AuthenticationDto.builder()
                .accessToken(accessToken)
                .build());

    }
    @Transactional
    public DataResult<AuthenticationDto> registerAdmin(UserRegisterRequestDto requestDto) {

        UserEntity user=userMapper.mapRegisterRequestDtoToEntity(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);
        UserEntity user1 = userRepository.findUserByEmail(requestDto.getEmail()).orElseThrow();
        RoleEntity role=roleRepository.findByName(ERole.ADMIN);
        UserRoleEntity userRole=UserRoleEntity.builder().
                user(user1)
                .role(role)
                .build();
        usersRolesRepository.save(userRole);
        var accessToken = jwtService.generateAccessToken(user1);
        return new DataResult<>("Succesfuly", HttpStatus.OK.value(),AuthenticationDto.builder()
                .accessToken(accessToken)
                .build());

    }
    public AuthenticationDto authenticateAdmin(AuthRequestDto authRequestDto) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword()
                )
        );
        UserEntity user = userRepository.findUserByEmail(authRequestDto.getEmail()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    public AuthenticationDto authenticate(AuthRequestDto authRequestDto) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword()
                )
        );
        UserEntity user = userRepository.findUserByEmail(authRequestDto.getEmail()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationDto generateAccessToken(String refreshToken) {
        String email = jwtService.extractUsernameRefresh(refreshToken);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
        if (jwtService.isRefreshTokenValid(refreshToken, userDetails)) {
            UserEntity user = userRepository.findUserByEmail(email).orElseThrow();
            var accessToken = jwtService.generateAccessToken(user);
            return AuthenticationDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new RuntimeException("Invalid refresh token");
        }
    }

    public void deleteUser(Long userId) {
        log.info("ActionLog.deleteUser.start");
        userRepository.deleteById(userId);
        log.info("ActionLog.deleteUser.end");
    }

//    public List<UserGetDto> getAllUser(){
//        return userMapper.mapEntitytoGetDtos(userRepository.findAll());
//    }

    public static UserEntity getUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

