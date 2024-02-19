package com.danny.vocabularynotebook.services;

import com.danny.vocabularynotebook.dtos.NotebookCreationDTO;
import com.danny.vocabularynotebook.dtos.NotebookViewDTO;
import com.danny.vocabularynotebook.entities.Notebook;
import com.danny.vocabularynotebook.mappers.NotebookMapper;
import com.danny.vocabularynotebook.repositories.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotebookService {
    private final NotebookRepository notebookRepository;
    private final NotebookMapper notebookMapper;

    public List<NotebookViewDTO> getNotebooks() {
        return notebookMapper.notebooksToNotebookViewDTOs(notebookRepository.findAll());
    }

    public NotebookViewDTO addNotebook(NotebookCreationDTO notebookCreationDTO) {
        Notebook notebookToSave = notebookMapper.notebookCreationDTOToNotebook(notebookCreationDTO);
        Notebook savedNotebook = notebookRepository.save(notebookToSave);
        return notebookMapper.notebookToNotebookViewDTO(savedNotebook);
    }

    public NotebookViewDTO getNotebookById(Long id) {
        Notebook foundNotebook = notebookRepository.findById(id).orElseThrow();
        return notebookMapper.notebookToNotebookViewDTO(foundNotebook);
    }

}
