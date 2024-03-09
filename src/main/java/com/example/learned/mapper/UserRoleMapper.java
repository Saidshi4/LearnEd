package com.example.learned.mapper;

import com.example.learned.entity.UserRoleEntity;
import com.example.learned.model.UserRoleDto;
import com.example.learned.model.request.UserRoleRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    @Mapping(target ="userId",source = "user.id")
    @Mapping(target="role",source = "role")
    UserRoleDto mapEntityToDto(UserRoleEntity userRoleEntity);
    @Mapping(target="user.id",source ="userId")
    @Mapping(target="role.id",source="roleId")
    UserRoleEntity mapRequestDtoToEntity(UserRoleRequestDto userRoleRequestDto);
}
