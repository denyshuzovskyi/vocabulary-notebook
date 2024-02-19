package com.danny.vocabularynotebook.mappers;

import com.danny.vocabularynotebook.dtos.JobStatusViewDTO;
import com.danny.vocabularynotebook.dtos.TestCollectionGenerationJobViewDTO;
import com.danny.vocabularynotebook.entities.TestCollectionGenerationJob;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TestCollectionGenerationJobMapper {
    TestCollectionGenerationJobViewDTO testCollectionGenerationJobToTestCollectionGenerationJobViewDTO(TestCollectionGenerationJob testCollectionGenerationJob);

    JobStatusViewDTO testCollectionGenerationJobToJobStatusViewDTO(TestCollectionGenerationJob testCollectionGenerationJob);
}
