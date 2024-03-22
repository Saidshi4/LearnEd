package com.example.learned.service;

import com.example.learned.dao.CategoryRepository;
import com.example.learned.dao.FaqRepository;
import com.example.learned.entity.CategoryEntity;
import com.example.learned.entity.FaqEntity;
import com.example.learned.mapper.CategoryMapper;
import com.example.learned.mapper.FaqMapper;
import com.example.learned.model.request.FaqRequestDto;
import com.example.learned.model.response.CategoryResponseDto;
import com.example.learned.model.response.FaqResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {
    private final FaqRepository faqRepository;
    private final FaqMapper faqMapper;
    public List<FaqResponseDto> getAllFaqs(){
        return faqMapper.mapEntityToResponses(faqRepository.findAll());
    }
    public void saveFaq(FaqRequestDto faqRequestDto){
        FaqEntity faqEntity=faqMapper.mapDtoToEntity(faqRequestDto);
         faqRepository.save(faqEntity);
    }
}
