package com.example.learned.controller;

import com.example.learned.model.DataResult;
import com.example.learned.model.request.FaqRequestDto;
import com.example.learned.model.response.FaqResponseDto;
import com.example.learned.model.response.TopicResponseDto;
import com.example.learned.service.FaqService;
import com.example.learned.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/api/faq")
@RequiredArgsConstructor
public class FaqController {
    private final FaqService faqService;
    @GetMapping("/getFaqs")
    public ResponseEntity<DataResult<List<FaqResponseDto>>> getAllFaqs(){

        return ResponseEntity.ok(new DataResult<>("Getting faqs successfully",
                HttpStatus.OK.value(), faqService.getAllFaqs()));

    }
    @PostMapping(value = "/saveFaq")
    public void saveFaq(@RequestBody FaqRequestDto faqRequestDto){
        faqService.saveFaq(faqRequestDto);
    }

}
