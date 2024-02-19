package com.danny.vocabularynotebook.dtos;

import lombok.Value;

@Value
public class TestCollectionViewDTO {
    Long id;
    Long notebookId;
    TestViewDTO[] tests;
    String createdAt;
}
