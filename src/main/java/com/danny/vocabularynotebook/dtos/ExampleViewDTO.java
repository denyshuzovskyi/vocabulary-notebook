package com.danny.vocabularynotebook.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class ExampleViewDTO {
    private Long id;
    private String exampleText;
}
