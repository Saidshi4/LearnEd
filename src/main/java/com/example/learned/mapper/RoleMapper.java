package com.example.learned.mapper;

import com.example.learned.entity.RoleEntity;
import com.example.learned.model.auth.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target="id",source="id")
    @Mapping(target="name",source="name")
    RoleDto mapEntityToDto(RoleEntity roleEntity);

}
