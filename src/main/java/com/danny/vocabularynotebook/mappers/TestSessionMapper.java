package com.danny.vocabularynotebook.mappers;

import com.danny.vocabularynotebook.dtos.TestSessionViewDTO;
import com.danny.vocabularynotebook.entities.TestSession;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestSessionMapper {

    TestSessionViewDTO testSessionToTestSessionViewDTO(TestSession testSession);
}
