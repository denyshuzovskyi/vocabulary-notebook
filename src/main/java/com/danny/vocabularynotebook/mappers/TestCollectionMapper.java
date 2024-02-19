package com.danny.vocabularynotebook.mappers;

import com.danny.vocabularynotebook.dtos.TestCollectionViewDTO;
import com.danny.vocabularynotebook.entities.TestCollection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = TestMapper.class
)
public interface TestCollectionMapper {

    @Mapping(target = "notebookId", source = "notebook.id")
    TestCollectionViewDTO testCollectionToTestCollectionViewDTO(TestCollection testCollection);
}
