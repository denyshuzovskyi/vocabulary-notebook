package com.danny.vocabularynotebook.services;

import com.danny.vocabularynotebook.dtos.TestSessionFinalResultDTO;
import com.danny.vocabularynotebook.dtos.TestSessionViewDTO;
import com.danny.vocabularynotebook.entities.TestCollection;
import com.danny.vocabularynotebook.entities.TestSession;
import com.danny.vocabularynotebook.mappers.TestSessionMapper;
import com.danny.vocabularynotebook.repositories.SelectedTestOptionRepository;
import com.danny.vocabularynotebook.repositories.TestCollectionRepository;
import com.danny.vocabularynotebook.repositories.TestOptionRepository;
import com.danny.vocabularynotebook.repositories.TestSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TestSessionService {
    private final TestSessionRepository testSessionRepository;
    private final TestCollectionRepository testCollectionRepository;
    private final TestOptionRepository testOptionRepository;
    private final SelectedTestOptionRepository selectedTestOptionRepository;
    private final TestSessionMapper testSessionMapper;

    public TestSessionViewDTO startTestSessionForTestCollection(Long testCollectionId) {
        TestCollection testCollection = testCollectionRepository.findById(testCollectionId).orElseThrow();

        TestSession testSession = new TestSession();
        testSession.setTestCollection(testCollection);
        testSession.setStartedAt(LocalDateTime.now());

        TestSession savedTestSession = testSessionRepository.save(testSession);

        return testSessionMapper.testSessionToTestSessionViewDTO(savedTestSession);
    }

    public TestSessionFinalResultDTO finishSessionAndGetFinalResult(Long testSessionId) {
        TestSession testSession = testSessionRepository.findById(testSessionId).orElseThrow();

        if (Objects.isNull(testSession.getFinishedAt())) {
            testSession.setFinishedAt(LocalDateTime.now());
            testSessionRepository.save(testSession);
        }

        Long testCollectionId = testSession.getTestCollection().getId();

        long totalPoints = testOptionRepository.getNumberOfCorrectTestOptionsInTestCollection(testCollectionId);
        long obtainedPoints = selectedTestOptionRepository.getNumberOfCorrectSelectedTestOptionsInTestSession(testSession.getId());
        Duration testSessionDuration = Duration.between(testSession.getStartedAt(), testSession.getFinishedAt());

        return new TestSessionFinalResultDTO(testSession.getId(), totalPoints, obtainedPoints, testSessionDuration.toSeconds());
    }
}
