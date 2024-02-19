package com.danny.vocabularynotebook.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NotebookViewDTO {
    private Long id;
    private String name;
    private String description;
}
