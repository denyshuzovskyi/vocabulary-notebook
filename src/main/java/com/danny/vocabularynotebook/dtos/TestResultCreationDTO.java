package com.danny.vocabularynotebook.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TestResultCreationDTO {
    private Long testId;
    private SelectedTestOptionCreationDTO[] selectedTestOptions;
    private Long testSessionId;
}
