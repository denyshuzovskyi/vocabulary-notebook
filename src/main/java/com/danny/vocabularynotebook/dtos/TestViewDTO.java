package com.danny.vocabularynotebook.dtos;

import lombok.Value;

@Value
public class TestViewDTO {
    Long id;
    String task;
    TestOptionViewDTO[] testOptions;
    String createdAt;
}
