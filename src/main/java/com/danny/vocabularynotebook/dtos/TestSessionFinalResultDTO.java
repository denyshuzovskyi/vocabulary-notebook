package com.danny.vocabularynotebook.dtos;

public record TestSessionFinalResultDTO(
        long testSessionId,
        long numberOfPointsTotal,
        long numberOfPointsObtained,
        long timeElapsedInSeconds) {
}
