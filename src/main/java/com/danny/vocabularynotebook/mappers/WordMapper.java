package com.danny.vocabularynotebook.mappers;

import com.danny.vocabularynotebook.dtos.WordCreationDTO;
import com.danny.vocabularynotebook.dtos.WordViewDTO;
import com.danny.vocabularynotebook.dtos.WordViewForTestCreationDTO;
import com.danny.vocabularynotebook.entities.Word;
import com.danny.vocabularynotebook.mappers.decorators.WordMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ExampleMapper.class
)
@DecoratedWith(WordMapperDecorator.class)
public interface WordMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "notebook.id", source = "notebookId")
    @Mapping(target = "examples", ignore = true)
    Word wordCreationDTOToWord(WordCreationDTO wordCreationDTO);

    WordViewDTO wordToWordViewDTO(Word word);

    List<WordViewDTO> wordsToWordViewDTOs(List<Word> words);

    WordViewForTestCreationDTO wordToWordViewForTestCreationDTO(Word word);
}
