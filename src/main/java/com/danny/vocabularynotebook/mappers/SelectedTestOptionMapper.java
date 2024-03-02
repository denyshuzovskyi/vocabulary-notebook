package com.danny.vocabularynotebook.mappers;

import com.danny.vocabularynotebook.dtos.SelectedTestOptionCreationDTO;
import com.danny.vocabularynotebook.entities.SelectedTestOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = TestOptionMapper.class
)
public interface SelectedTestOptionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "selectedOption.id", source = "testOptionId")
    @Mapping(target = "testResult", ignore = true)
    SelectedTestOption selectedTestOptionCreationDTOToSelectedTestOption(SelectedTestOptionCreationDTO selectedTestOptionCreationDTO);
}
