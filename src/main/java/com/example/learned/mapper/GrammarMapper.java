package com.example.learned.mapper;

import com.example.learned.entity.GrammarEntity;
import com.example.learned.entity.LevelEntity;
import com.example.learned.entity.VocabularyEntity;
import com.example.learned.model.response.GrammarResponseDto;
import com.example.learned.model.response.LevelResponseDto;
import com.example.learned.model.response.LevelRoomResponseDto;
import com.example.learned.model.response.VocabularyResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GrammarMapper {
    GrammarResponseDto mapEntityToGrammarResponseDto(GrammarEntity grammarEntity);
    List<GrammarResponseDto> mapEntityToGrammarResponseDtos(List<GrammarEntity> grammarEntities);


}
