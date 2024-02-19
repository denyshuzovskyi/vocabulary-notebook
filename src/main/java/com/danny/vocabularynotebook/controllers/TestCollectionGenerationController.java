package com.danny.vocabularynotebook.controllers;

import com.danny.vocabularynotebook.dtos.JobStatusViewDTO;
import com.danny.vocabularynotebook.dtos.TestCollectionGenerationJobViewDTO;
import com.danny.vocabularynotebook.services.TestCollectionGenerationJobService;
import com.danny.vocabularynotebook.services.TestGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test-collection-generation")
@RequiredArgsConstructor
@CrossOrigin
public class TestCollectionGenerationController {
    private final TestGenerationService testGenerationService;
    private final TestCollectionGenerationJobService testCollectionGenerationJobService;

    @PostMapping("/trigger/for-notebook/{id}")
    public TestCollectionGenerationJobViewDTO triggerTestCollectionGenerationJob(@PathVariable Long id) {
        return testGenerationService.triggerTestCollectionGenerationForNotebook(id);
    }

    @GetMapping("/status/{id}")
    public JobStatusViewDTO checkJobStatus(@PathVariable Long id) {
        return testCollectionGenerationJobService.getJobStatus(id);
    }

}
