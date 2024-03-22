package com.example.learned.controller;

import com.example.learned.model.DataResult;
import com.example.learned.model.response.VocabularyResponseDto;
import com.example.learned.service.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api/vocabulary")
@RequiredArgsConstructor
public class VocabularyController {
    private final VocabularyService vocabularyService;
    @GetMapping("/getVocabulary")
    public ResponseEntity<DataResult<List<VocabularyResponseDto>>> getAllVocabulary(@RequestParam Long topicId
            ,@RequestParam Long levelId){

        return ResponseEntity.ok(new DataResult<>("Getting vocabulary successfully",
                HttpStatus.OK.value(), vocabularyService.getAllVocabularies(topicId,levelId)));

    }
}
