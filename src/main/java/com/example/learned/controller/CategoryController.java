package com.example.learned.controller;

import com.example.learned.model.DataResult;
import com.example.learned.model.response.CategoryResponseDto;
import com.example.learned.model.response.TopicResponseDto;
import com.example.learned.service.CategoryService;
import com.example.learned.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api/topic")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/getCategoryWithPaging")
    public ResponseEntity<DataResult<List<CategoryResponseDto>>> getTopicWithPaging(@RequestParam(defaultValue = "0") Integer pageNo,
                                                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(new DataResult<>("Getting category successfully",
                HttpStatus.OK.value(), categoryService.getAllCategory(pageNo, pageSize)));
    }


}
