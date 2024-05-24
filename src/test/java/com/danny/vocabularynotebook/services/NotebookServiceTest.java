package com.danny.vocabularynotebook.services;

import com.danny.vocabularynotebook.dtos.NotebookViewDTO;
import com.danny.vocabularynotebook.entities.Notebook;
import com.danny.vocabularynotebook.mappers.NotebookMapper;
import com.danny.vocabularynotebook.repositories.NotebookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotebookServiceTest {

    @Mock
    private NotebookRepository notebookRepository;

    @Mock
    private NotebookMapper notebookMapper;

    @InjectMocks
    private NotebookService notebookService;


    @Test
    public void testGetNotebookById() {
        // Mock the behavior of the repository
        Long notebookId = 1L;
        Notebook notebook = new Notebook();
        notebook.setId(notebookId);
        when(notebookRepository.findById(notebookId)).thenReturn(Optional.of(notebook));

        // Mock the behavior of the mapper
        NotebookViewDTO expectedNotebookViewDTO = new NotebookViewDTO(1L, "Test", "Test description");
        when(notebookMapper.notebookToNotebookViewDTO(notebook)).thenReturn(expectedNotebookViewDTO);

        // Call the method under test
        NotebookViewDTO result = notebookService.getNotebookById(notebookId);

        // Verify that the repository method was called with the correct argument
        verify(notebookRepository).findById(notebookId);

        // Verify that the mapper method was called with the correct argument
        verify(notebookMapper).notebookToNotebookViewDTO(notebook);

        // Verify that the result matches the expected DTO
        assertEquals(expectedNotebookViewDTO, result);
    }
}
