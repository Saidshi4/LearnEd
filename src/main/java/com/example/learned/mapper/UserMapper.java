package com.example.learned.mapper;
import com.example.learned.entity.*;
import com.example.learned.model.UserGetDto;
import com.example.learned.model.UserGetDto2;
import com.example.learned.model.UserRoleDto;
import com.example.learned.model.auth.RoleDto;
import com.example.learned.model.auth.UserRegisterRequestDto;
import com.example.learned.model.response.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "imageData", expression = "java(mapImage(userEntity.getImageData()))")
    UserResponseDto mapEntityToResponseDto(UserEntity userEntity);
    UserGetDto2 mapEntityToGetDto2(UserEntity userEntity);

    List<UserGetDto2> mapEntityToGetDtos2(List<UserEntity> userEntity);
    UserEntity mapRegisterRequestDtoToEntity(UserRegisterRequestDto userRegisterRequestDto);
    @Mapping(target = "userRoles", expression = "java(mapUserRoleEntitiesToDtos(userEntity.getUserRoles()))")
    UserGetDto mapEntitytoGetDto(UserEntity userEntity);

    List<UserVerifyResponseDto> mapEntityToVerifyDtos(List<UserEntity> userEntity);
    default String mapImage(byte[] imageData){
        return java.util.Base64.getEncoder().encodeToString(imageData);

    }

    default List<UserRoleDto> mapUserRoleEntitiesToDtos(List<UserRoleEntity> userRoleEntities) {
        return userRoleEntities.stream()  // Use the getter here
                .map(userRoleEntity -> new UserRoleDto(userRoleEntity.getId(), userRoleEntity.getUser().getId(), this.mapRoleEntitiToDto(userRoleEntity.getRole())))
                .collect(Collectors.toList());
    }

    default RoleDto mapRoleEntitiToDto(RoleEntity roleEntity){
        return new RoleDto(roleEntity.getId(), roleEntity.getName());
    }

}
