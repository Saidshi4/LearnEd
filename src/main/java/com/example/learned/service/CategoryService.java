package com.example.learned.service;

import com.example.learned.dao.CategoryRepository;
import com.example.learned.dao.TopicRepository;
import com.example.learned.entity.CategoryEntity;
import com.example.learned.entity.TopicEntity;
import com.example.learned.mapper.CategoryMapper;
import com.example.learned.mapper.TopicMapper;
import com.example.learned.model.response.CategoryResponseDto;
import com.example.learned.model.response.TopicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public List<CategoryResponseDto> getAllCategory(int pageNo, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("name").ascending());
        Page<CategoryEntity> pagingCategory = categoryRepository.findAll(pageRequest);
        return categoryMapper.mapEntityToCategoryResponses(pagingCategory.getContent());

    }
}
