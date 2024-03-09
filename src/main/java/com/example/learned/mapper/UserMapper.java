package com.example.learned.mapper;
import com.example.learned.entity.*;
import com.example.learned.model.UserGetDto;
import com.example.learned.model.UserGetDto2;
import com.example.learned.model.UserRoleDto;
import com.example.learned.model.auth.RoleDto;
import com.example.learned.model.auth.UserRegisterRequestDto;
import com.example.learned.model.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
   // @Mapping(target = "imageUrl", expression = "java(mapImageUrl(userEntity.getUserFileEntity()))")
    UserResponseDto mapEntityToResponseDto(UserEntity userEntity);
//    PendingUserResponseDto mapEntityToPendingDto(UserEntity userEntity);
//    List<PendingUserResponseDto> mapEntityToPendingDtos(List<UserEntity> userEntities);
    UserGetDto2 mapEntityToGetDto2(UserEntity userEntity);

    //List<UserGetDto2> mapEntityToGetDtos2(List<UserEntity> userEntity);
    UserEntity mapRegisterRequestDtoToEntity(UserRegisterRequestDto userRegisterRequestDto);
    @Mapping(target = "userRoles", expression = "java(mapUserRoleEntitiesToDtos(userEntity.getUserRoles()))")
    UserGetDto mapEntitytoGetDto(UserEntity userEntity);
//    @Mapping(target = "subUserResponseDtos", expression = "java(mapEntityToVerifyResponseDto(userEntity.getUserEntities()))")
//    UserVerifyResponseDto mapEntityToVerifyDto(UserEntity userEntity);
    List<UserVerifyResponseDto> mapEntityToVerifyDtos(List<UserEntity> userEntity);
//    default List<SubUserResponseDto> mapEntityToVerifyResponseDto(List<SubUserEntity> subUserEntities){
//        return subUserEntities.stream()
//                .map(subUserEntity -> new SubUserResponseDto(subUserEntity.getId(),subUserEntity.getEGender(),subUserEntity.getFirstName(),subUserEntity.getLastName(),
//                        subUserEntity.getFatherName(),subUserEntity.getBirthPlace(), subUserEntity.getBirthDate(),subUserEntity.getWorkPlace(),
//                        subUserEntity.getWorkDescription(),subUserEntity.getEducationInstitution())).collect(Collectors.toList());
//    }

    default List<UserRoleDto> mapUserRoleEntitiesToDtos(List<UserRoleEntity> userRoleEntities) {
        return userRoleEntities.stream()  // Use the getter here
                .map(userRoleEntity -> new UserRoleDto(userRoleEntity.getId(), userRoleEntity.getUser().getId(), this.mapRoleEntitiToDto(userRoleEntity.getRole())))
                .collect(Collectors.toList());
    }
//    default List<ImageResponseDto> mapImageUrl(List<UserFileEntity> userFileEntities){
//        return userFileEntities.stream()
//                .map(userFileEntity->new ImageResponseDto(userFileEntity.getImageUrl()))
//                .collect(Collectors.toList());
//    }
    default RoleDto mapRoleEntitiToDto(RoleEntity roleEntity){
        return new RoleDto(roleEntity.getId(), roleEntity.getName());
    }

}
