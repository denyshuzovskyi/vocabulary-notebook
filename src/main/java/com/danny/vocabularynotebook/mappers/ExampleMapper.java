package com.danny.vocabularynotebook.mappers;

import com.danny.vocabularynotebook.dtos.ExampleCreationDTO;
import com.danny.vocabularynotebook.dtos.ExampleViewDTO;
import com.danny.vocabularynotebook.entities.Example;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExampleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "word", ignore = true)
    Example exampleCreationDTOToExample(ExampleCreationDTO exampleCreationDTO);
    ExampleViewDTO exampleToExampleViewDTO(Example example);
}
