package com.danny.vocabularynotebook.controllers;

import com.danny.vocabularynotebook.dtos.NotebookCreationDTO;
import com.danny.vocabularynotebook.dtos.NotebookViewDTO;
import com.danny.vocabularynotebook.services.NotebookService;
import com.danny.vocabularynotebook.testconfigs.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = NotebookController.class)
@Import(SecurityConfig.class)
@AutoConfigureMockMvc
class NotebookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    NotebookService notebookService;

    private final List<NotebookViewDTO> notebooks = List.of(
            new NotebookViewDTO(1L, "Notebook 1", "Test notebook"),
            new NotebookViewDTO(2L, "Notebook 2", "Test notebook")
    );

    @Test
    void getNotebooks() throws Exception {
        String expectedJson = """
            [
                {
                    "id": 1,
                    "name": "Notebook 1",
                    "description": "Test notebook"
                },
                {
                    "id": 2,
                    "name": "Notebook 2",
                    "description": "Test notebook"
                }
            ]
            """;

        when(notebookService.getNotebooks()).thenReturn(notebooks);

        ResultActions resultActions = mockMvc.perform(get("/notebooks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expectedJson));

        String actualJson = resultActions.andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

    @Test
    void addNotebook() throws Exception {
        NotebookViewDTO savedNotebook = new NotebookViewDTO(3L, "Notebook 3", "Test notebook");
        when(notebookService.addNotebook(any(NotebookCreationDTO.class))).thenReturn(savedNotebook);

        String notebookToCreateJson = """
            {
                "name": "Notebook 3",
                "description": "Test notebook"
            }
            """;
        String expectedNotebookViewDTOJson = """
            {
                "id": 3,
                "name": "Notebook 3",
                "description": "Test notebook"
            }
            """;

        mockMvc.perform(
                post("/notebooks")
                .contentType("application/json")
                .content(notebookToCreateJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expectedNotebookViewDTOJson));

        ArgumentCaptor<NotebookCreationDTO> captor = forClass(NotebookCreationDTO.class);
        verify(notebookService).addNotebook(captor.capture());

        NotebookCreationDTO capturedNotebook = captor.getValue();
        assertEquals("Notebook 3", capturedNotebook.getName());
        assertEquals("Test notebook", capturedNotebook.getDescription());
    }

    @Test
    void getNotebookById() throws Exception {
        when(notebookService.getNotebookById(1L)).thenReturn(notebooks.get(0));

        String expectedNotebookViewDTOJson = """
            {
                "id": 1,
                "name": "Notebook 1",
                "description": "Test notebook"
            }
            """;

        mockMvc.perform(get("/notebooks/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(expectedNotebookViewDTOJson));
    }
}