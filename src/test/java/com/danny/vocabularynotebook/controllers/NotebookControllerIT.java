package com.danny.vocabularynotebook.controllers;

import com.danny.vocabularynotebook.dtos.NotebookCreationDTO;
import com.danny.vocabularynotebook.dtos.NotebookViewDTO;
import com.danny.vocabularynotebook.repositories.NotebookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles(profiles = "test-containers")
@Sql(scripts = "/sql/notebook_1.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "/sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
class NotebookControllerIT {
    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    NotebookRepository notebookRepository;

    @Test
    void getNotebooks() {
        ResponseEntity<NotebookViewDTO[]> responseEntity = restTemplate.getForEntity("/notebooks", NotebookViewDTO[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        NotebookViewDTO[] notebooks = responseEntity.getBody();

        assertEquals(1, Objects.requireNonNull(notebooks).length);
        assertEquals("Essential English Words 1", notebooks[0].getName());
    }

    @Test
    void addNotebook() {
        NotebookCreationDTO notebookCreationDTO = new NotebookCreationDTO("Notebook 2", "Test Notebook");
        ResponseEntity<NotebookViewDTO> responseEntity = restTemplate.postForEntity("/notebooks", notebookCreationDTO, NotebookViewDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        NotebookViewDTO notebook = responseEntity.getBody();
        assertEquals(notebookCreationDTO.getName(), Objects.requireNonNull(notebook).getName());

        // Cleanup
        notebookRepository.deleteById(notebook.getId());
        assertFalse(notebookRepository.findById(notebook.getId()).isPresent());
    }

    @Test
    void getNotebookById() {
        ResponseEntity<NotebookViewDTO> responseEntity = restTemplate.getForEntity("/notebooks/1", NotebookViewDTO.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        NotebookViewDTO notebook = responseEntity.getBody();

        assertEquals("Essential English Words 1", Objects.requireNonNull(notebook).getName());
    }
}