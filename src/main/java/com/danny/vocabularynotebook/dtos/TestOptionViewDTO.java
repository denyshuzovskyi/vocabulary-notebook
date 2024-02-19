package com.danny.vocabularynotebook.dtos;

import lombok.Value;

@Value
public class TestOptionViewDTO {
    Long id;
    String option;
    Boolean isCorrect;
}
