package com.danny.vocabularynotebook.services;

import com.danny.vocabularynotebook.controllers.WordDoesNotExistException;
import com.danny.vocabularynotebook.dtos.WordCreationDTO;
import com.danny.vocabularynotebook.dtos.WordViewDTO;
import com.danny.vocabularynotebook.entities.Word;
import com.danny.vocabularynotebook.mappers.WordMapper;
import com.danny.vocabularynotebook.repositories.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {
    private final WordRepository wordRepository;
    private final WordMapper wordMapper;

    public List<WordViewDTO> getAllWordsInNotebook(Long notebookId) {
        List<Word> foundWords = wordRepository.findAllWithExamplesByNotebookId(notebookId);
        return wordMapper.wordsToWordViewDTOs(foundWords);
    }

    public WordViewDTO addWord(WordCreationDTO wordCreationDTO) {
        Word wordToSave = wordMapper.wordCreationDTOToWord(wordCreationDTO);
        Word savedWord = wordRepository.save(wordToSave);
        return wordMapper.wordToWordViewDTO(savedWord);
    }

    public Word getWord(Long id) {
        return wordRepository.findById(id)
                .orElseThrow(() -> new WordDoesNotExistException(id));
    }

    public void deleteWord(Long id) {
        wordRepository.delete(
                wordRepository.findById(id)
                        .orElseThrow(() -> new WordDoesNotExistException(id))
        );
    }
}
