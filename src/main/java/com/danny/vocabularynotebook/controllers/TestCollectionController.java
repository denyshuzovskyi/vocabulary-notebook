package com.danny.vocabularynotebook.controllers;

import com.danny.vocabularynotebook.dtos.TestCollectionViewDTO;
import com.danny.vocabularynotebook.services.TestCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test-collections")
@RequiredArgsConstructor
@CrossOrigin
public class TestCollectionController {
    private final TestCollectionService testCollectionService;

    @GetMapping("/exists/for-notebook/{id}")
    public ResponseEntity<Void> existsForNotebookId(@PathVariable Long id) {
        boolean testCollectionExists = testCollectionService.existsForNotebookId(id);

        if (testCollectionExists) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-notebook/{id}")
    public TestCollectionViewDTO getLastTestCollectionByNotebookId(@PathVariable Long id) {
        return testCollectionService.getLastTestCollectionByNotebookId(id);
    }
}
