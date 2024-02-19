package com.danny.vocabularynotebook.services;

import com.danny.vocabularynotebook.dtos.JobStatusViewDTO;
import com.danny.vocabularynotebook.entities.TestCollectionGenerationJob;
import com.danny.vocabularynotebook.mappers.TestCollectionGenerationJobMapper;
import com.danny.vocabularynotebook.repositories.TestCollectionGenerationJobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestCollectionGenerationJobService {
    private final TestCollectionGenerationJobRepository testCollectionGenerationJobRepository;
    private final TestCollectionGenerationJobMapper testCollectionGenerationJobMapper;

    public JobStatusViewDTO getJobStatus(Long jobId) {
        TestCollectionGenerationJob testCollectionGenerationJob = testCollectionGenerationJobRepository.findById(jobId).orElseThrow();
        return testCollectionGenerationJobMapper.testCollectionGenerationJobToJobStatusViewDTO(testCollectionGenerationJob);
    }
}
