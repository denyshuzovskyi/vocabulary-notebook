package com.danny.vocabularynotebook.repositories;

import com.danny.vocabularynotebook.entities.Notebook;
import com.danny.vocabularynotebook.entities.Word;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = "/sql/notebook_1.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class WordRepositoryTest {
    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private WordRepository wordRepository;

    @Value("${my.custom.property}")
    private String customProperty;

    @Test
    void testCustomProperty() {
        Assertions.assertEquals("test", customProperty);
    }

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
