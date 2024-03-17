package com.example.learned.mapper;

import com.example.learned.entity.CategoryEntity;
import com.example.learned.model.response.CategoryResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponseDto mapEntityToCategoryResponse(CategoryEntity categoryEntity);
    List<CategoryResponseDto> mapEntityToCategoryResponses(List<CategoryEntity> categoryEntityList);

}
