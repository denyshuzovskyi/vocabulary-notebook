package com.danny.vocabularynotebook.controllers;

public class WordDoesNotExistException extends RuntimeException {
    public WordDoesNotExistException(Long id) {
        super(String.format("No word with id = %d exists", id));
    }
}
