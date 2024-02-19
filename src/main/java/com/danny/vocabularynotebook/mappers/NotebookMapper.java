package com.danny.vocabularynotebook.mappers;

import com.danny.vocabularynotebook.dtos.NotebookCreationDTO;
import com.danny.vocabularynotebook.dtos.NotebookViewDTO;
import com.danny.vocabularynotebook.entities.Notebook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotebookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "words", ignore = true)
    Notebook notebookCreationDTOToNotebook(NotebookCreationDTO notebookCreationDTO);

    NotebookViewDTO notebookToNotebookViewDTO(Notebook notebook);

    @Mapping(target = "words", ignore = true)
    Notebook notebookViewDTOToNotebook(NotebookViewDTO notebookViewDTO);

    List<NotebookViewDTO> notebooksToNotebookViewDTOs(List<Notebook> notebooks);
    List<Notebook> notebookViewDTOsToNotebooks(List<NotebookViewDTO> notebookViewDTOs);
}
