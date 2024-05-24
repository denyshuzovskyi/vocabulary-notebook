package com.danny.vocabularynotebook.controllers;

import com.danny.vocabularynotebook.dtos.TestSessionFinalResultDTO;
import com.danny.vocabularynotebook.dtos.TestSessionViewDTO;
import com.danny.vocabularynotebook.services.TestSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test-sessions")
@RequiredArgsConstructor
@CrossOrigin
public class TestSessionController {
    private final TestSessionService testSessionService;

    @PostMapping("/start/for-test-collection/{id}")
    public TestSessionViewDTO startTestSessionForTestCollection(@PathVariable Long id) {
        return testSessionService.startTestSessionForTestCollection(id);
    }

    @GetMapping(path = "/{id}/final-result")
    public TestSessionFinalResultDTO finishSessionAndGetFinalResult(@PathVariable Long id) {
        return testSessionService.finishSessionAndGetFinalResult(id);
    }
}
