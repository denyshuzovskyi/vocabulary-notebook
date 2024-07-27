package com.danny.vocabularynotebook.repositories;

import com.danny.vocabularynotebook.entities.Notebook;
import com.danny.vocabularynotebook.entities.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles(profiles = "test-containers")
@Sql(scripts = "/sql/notebook_1.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "/sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class WordRepositoryContainersTest {
    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private WordRepository wordRepository;

    @Test
    void testFindAllByNotebookId() {
        Assertions.assertEquals(1, notebookRepository.count());

        Notebook notebook = new Notebook();
        notebook.setName("Notebook");
        notebook = notebookRepository.save(notebook);

        Assertions.assertEquals(2, notebookRepository.count());

        Word word = new Word();
        word.setWord("Test");
        word.setNotebook(notebook);
        wordRepository.save(word);

        List<Word> words = wordRepository.findAllByNotebookId(notebook.getId());
        Assertions.assertEquals(words.size(), 1);
        Assertions.assertEquals(words.get(0).getWord(), "Test");
    }
}
