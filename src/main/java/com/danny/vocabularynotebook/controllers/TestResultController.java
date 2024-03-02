package com.danny.vocabularynotebook.controllers;

import com.danny.vocabularynotebook.dtos.TestResultCreationDTO;
import com.danny.vocabularynotebook.services.TestResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test-results")
@RequiredArgsConstructor
@CrossOrigin
public class TestResultController {
    private final TestResultService testResultService;

    @PostMapping
    public ResponseEntity<Void> addTestResult(@RequestBody TestResultCreationDTO testResult) {
        try {
            testResultService.saveTestResult(testResult);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
