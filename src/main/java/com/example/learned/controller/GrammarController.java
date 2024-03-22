package com.example.learned.controller;

import com.example.learned.model.DataResult;
import com.example.learned.model.response.GrammarResponseDto;
import com.example.learned.model.response.VocabularyResponseDto;
import com.example.learned.service.GrammarService;
import com.example.learned.service.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/api/grammar")
@RequiredArgsConstructor
public class GrammarController {
    private final GrammarService grammarService;
    @GetMapping("/getGrammars")
    public ResponseEntity<DataResult<List<GrammarResponseDto>>> getAllVocabulary(@RequestParam Long topicId
            , @RequestParam Long levelId){

        return ResponseEntity.ok(new DataResult<>("Getting grammar successfully",
                HttpStatus.OK.value(), grammarService.getAllGrammars(topicId,levelId)));

    }
}
