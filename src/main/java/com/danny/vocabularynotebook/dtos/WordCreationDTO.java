package com.danny.vocabularynotebook.dtos;

import com.danny.vocabularynotebook.entities.Example;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WordCreationDTO {
    private String word;
    private String pos;
    private String pronunciation;
    private String definition;
    private ExampleCreationDTO[] examples;
    private Long notebookId;
}
