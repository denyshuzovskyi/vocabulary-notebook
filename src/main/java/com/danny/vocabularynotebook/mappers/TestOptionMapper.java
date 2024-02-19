package com.danny.vocabularynotebook.mappers;

import com.danny.vocabularynotebook.dtos.TestOptionGeneratedDTO;
import com.danny.vocabularynotebook.dtos.TestOptionViewDTO;
import com.danny.vocabularynotebook.entities.TestOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestOptionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "option", source = "definition")
    @Mapping(target = "test", ignore = true)
    TestOption testOptionGeneratedDTOToTestOption(TestOptionGeneratedDTO testOptionGeneratedDTO);

    TestOptionViewDTO testOptionToTestOptionViewDTO(TestOption testOption);
}
