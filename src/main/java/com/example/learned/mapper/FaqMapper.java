package com.example.learned.mapper;

import com.example.learned.entity.CategoryEntity;
import com.example.learned.entity.FaqEntity;
import com.example.learned.model.request.FaqRequestDto;
import com.example.learned.model.response.CategoryResponseDto;
import com.example.learned.model.response.FaqResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FaqMapper {
    FaqEntity mapDtoToEntity(FaqRequestDto faqRequestDto);
    FaqResponseDto mapEntityToResponse(FaqEntity faqEntity);
    List<FaqResponseDto> mapEntityToResponses(List<FaqEntity> faqEntities);

}
