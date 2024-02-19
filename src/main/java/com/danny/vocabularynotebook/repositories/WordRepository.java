package com.danny.vocabularynotebook.repositories;

import com.danny.vocabularynotebook.entities.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    @Query("SELECT w FROM Word w LEFT JOIN FETCH w.examples WHERE w.notebook.id = :notebookId")
    List<Word> findAllWithExamplesByNotebookId(@Param("notebookId") Long notebookId);

    @Query("SELECT w FROM Word w WHERE w.notebook.id = :notebookId")
    List<Word> findAllByNotebookId(@Param("notebookId") Long notebookId);
}
