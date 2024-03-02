package com.danny.vocabularynotebook.mappers;

import com.danny.vocabularynotebook.dtos.TestResultCreationDTO;
import com.danny.vocabularynotebook.entities.TestResult;
import com.danny.vocabularynotebook.mappers.decorators.TestResultMapperDecorator;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = SelectedTestOptionMapper.class
)
@DecoratedWith(TestResultMapperDecorator.class)
public interface TestResultMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "test.id", source = "testId")
    @Mapping(target = "selectedTestOptions", ignore = true)
    @Mapping(target = "testSession.id", source = "testSessionId")
    TestResult testResultCreationDTOToTestResult(TestResultCreationDTO testResultCreationDTO);
}
