package com.danny.vocabularynotebook.controllers;

import com.danny.vocabularynotebook.dtos.NotebookCreationDTO;
import com.danny.vocabularynotebook.dtos.NotebookViewDTO;
import com.danny.vocabularynotebook.services.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/notebooks")
@RequiredArgsConstructor
@CrossOrigin
public class NotebookController {
    private final NotebookService notebookService;

    @GetMapping
    public List<NotebookViewDTO> getNotebooks() {
        return notebookService.getNotebooks();
    }

    @PostMapping()
    public NotebookViewDTO addNotebook(@RequestBody NotebookCreationDTO notebook) {
        return notebookService.addNotebook(notebook);
    }

    @GetMapping(path = "/{id}")
    public NotebookViewDTO getNotebookById(@PathVariable Long id) {
        return notebookService.getNotebookById(id);
    }
}
