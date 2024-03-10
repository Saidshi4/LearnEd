package com.example.learned.mapper;

import com.example.learned.entity.ImageEntity;
import com.example.learned.model.ImageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(target="user.id",source = "userId")
    ImageEntity dtoToEntity(ImageDto imageDto);
}
