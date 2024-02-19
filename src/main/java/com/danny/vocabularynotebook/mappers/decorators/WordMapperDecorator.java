package com.danny.vocabularynotebook.mappers.decorators;

import com.danny.vocabularynotebook.dtos.ExampleCreationDTO;
import com.danny.vocabularynotebook.dtos.WordCreationDTO;
import com.danny.vocabularynotebook.entities.Example;
import com.danny.vocabularynotebook.entities.Word;
import com.danny.vocabularynotebook.mappers.ExampleMapper;
import com.danny.vocabularynotebook.mappers.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public abstract class WordMapperDecorator implements WordMapper {
    @Autowired
    @Qualifier("delegate")
    private WordMapper delegate;

    @Autowired
    private ExampleMapper exampleMapper;

    @Override
    public Word wordCreationDTOToWord(WordCreationDTO wordCreationDTO) {
        Word word = delegate.wordCreationDTOToWord(wordCreationDTO);
        if (wordCreationDTO.getExamples() != null) {
            for (ExampleCreationDTO exampleDto : wordCreationDTO.getExamples()) {
                Example example = exampleMapper.exampleCreationDTOToExample(exampleDto);
                word.addExample(example);
            }
        }

        return word;
    }
}
