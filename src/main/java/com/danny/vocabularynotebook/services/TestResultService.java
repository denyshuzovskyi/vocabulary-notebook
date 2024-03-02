package com.danny.vocabularynotebook.services;

import com.danny.vocabularynotebook.dtos.SelectedTestOptionCreationDTO;
import com.danny.vocabularynotebook.dtos.TestResultCreationDTO;
import com.danny.vocabularynotebook.entities.Test;
import com.danny.vocabularynotebook.entities.TestResult;
import com.danny.vocabularynotebook.entities.TestSession;
import com.danny.vocabularynotebook.mappers.TestResultMapper;
import com.danny.vocabularynotebook.repositories.TestOptionRepository;
import com.danny.vocabularynotebook.repositories.TestRepository;
import com.danny.vocabularynotebook.repositories.TestResultRepository;
import com.danny.vocabularynotebook.repositories.TestSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TestResultService {
    private final TestResultRepository testResultRepository;
    private final TestSessionRepository testSessionRepository;
    private final TestRepository testRepository;
    private final TestOptionRepository testOptionRepository;
    private final TestResultMapper testResultMapper;

    public TestResult saveTestResult(TestResultCreationDTO testResultCreationDTO) throws NoSuchFieldException {
        TestSession testSession = testSessionRepository.findById(testResultCreationDTO.getTestSessionId()).orElseThrow(
                () -> new NoSuchElementException(
                        String.format("TestSession with id = %s does not exist", testResultCreationDTO.getTestSessionId())
                )
        );
        if (Objects.nonNull(testSession.getFinishedAt())) {
            throw new IllegalStateException("Unable to save TestResult because TestSession is finished");
        }


        Test test = testRepository.findById(testResultCreationDTO.getTestId()).orElseThrow(
                () -> new NoSuchElementException(
                        String.format("Test with id = %s does not exist", testResultCreationDTO.getTestId())
                )
        );


//        if(!testRepository.existsById(testResultCreationDTO.getTestId())) {
//            throw new NoSuchElementException(String.format("Test with id = %s does not exist", testResultCreationDTO.getTestId()));
//        }
        for (SelectedTestOptionCreationDTO selectedTestOptionCreationDTO : testResultCreationDTO.getSelectedTestOptions()) {
            if(!testOptionRepository.existsById(selectedTestOptionCreationDTO.getTestOptionId())) {
                throw new NoSuchElementException(String.format("TestOption with id = %s does not exist", selectedTestOptionCreationDTO.getTestOptionId()));
            }
        }

        TestResult testResult = testResultMapper.testResultCreationDTOToTestResult(testResultCreationDTO);

        return testResultRepository.save(testResult);
    }

}
