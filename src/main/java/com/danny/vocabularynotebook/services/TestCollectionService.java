package com.danny.vocabularynotebook.services;

import com.danny.vocabularynotebook.dtos.TestCollectionViewDTO;
import com.danny.vocabularynotebook.entities.Test;
import com.danny.vocabularynotebook.entities.TestCollection;
import com.danny.vocabularynotebook.mappers.TestCollectionMapper;
import com.danny.vocabularynotebook.repositories.TestCollectionRepository;
import com.danny.vocabularynotebook.repositories.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestCollectionService {
    private final TestCollectionRepository testCollectionRepository;
    private final TestRepository testRepository;
    private final TestCollectionMapper testCollectionMapper;

    public TestCollectionViewDTO getLastTestCollectionByNotebookId(Long notebookId) {
        TestCollection testCollection = testCollectionRepository.findLatestByNotebookId(notebookId).orElseThrow();
        List<Test> tests = testRepository.findAllWithTestOptionsByTestCollectionId(testCollection.getId());
        testCollection.setTests(tests);

        return testCollectionMapper.testCollectionToTestCollectionViewDTO(testCollection);
    }

    public boolean existsForNotebookId(Long notebookId) {
        return testCollectionRepository.existsForNotebookId(notebookId);
    }
}
