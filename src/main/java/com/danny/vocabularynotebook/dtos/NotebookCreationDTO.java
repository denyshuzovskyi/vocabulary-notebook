package com.danny.vocabularynotebook.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NotebookCreationDTO {
    private String name;
    private String description;
}
