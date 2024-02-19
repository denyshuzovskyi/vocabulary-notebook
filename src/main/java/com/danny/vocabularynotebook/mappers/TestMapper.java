package com.danny.vocabularynotebook.mappers;

import com.danny.vocabularynotebook.dtos.TestViewDTO;
import com.danny.vocabularynotebook.entities.Test;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = TestOptionMapper.class
)
public interface TestMapper {
    TestViewDTO testToTestViewDTO(Test test);
}
