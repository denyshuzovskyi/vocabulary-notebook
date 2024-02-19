package com.danny.vocabularynotebook.dtos;

import lombok.Value;

@Value
public class WordViewDTO {
    Long id;
    String word;
    String pos;
    String pronunciation;
    String definition;
    ExampleViewDTO[] examples;
}
