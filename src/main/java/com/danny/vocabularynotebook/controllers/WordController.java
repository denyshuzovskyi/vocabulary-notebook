package com.danny.vocabularynotebook.controllers;

import com.danny.vocabularynotebook.dtos.WordCreationDTO;
import com.danny.vocabularynotebook.dtos.WordViewDTO;
import com.danny.vocabularynotebook.entities.Word;
import com.danny.vocabularynotebook.services.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/words")
@RequiredArgsConstructor
@CrossOrigin
public class WordController {
    private final WordService service;

    @GetMapping(path = "/by-notebook/{id}")
    public List<WordViewDTO> getAllWordsInNotebook(@PathVariable Long id) {
        return service.getAllWordsInNotebook(id);
    }


    @PostMapping
    public WordViewDTO addWord(@RequestBody WordCreationDTO word) {
        return service.addWord(word);
    }

    @GetMapping(path ="/{id}")
    public Word getWord(@PathVariable Long id) {
        return service.getWord(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteWord(@PathVariable Long id) {
        service.deleteWord(id);
    }
}
