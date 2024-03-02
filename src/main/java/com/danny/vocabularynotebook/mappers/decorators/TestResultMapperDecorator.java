package com.danny.vocabularynotebook.mappers.decorators;

import com.danny.vocabularynotebook.dtos.SelectedTestOptionCreationDTO;
import com.danny.vocabularynotebook.dtos.TestResultCreationDTO;
import com.danny.vocabularynotebook.entities.SelectedTestOption;
import com.danny.vocabularynotebook.entities.TestResult;
import com.danny.vocabularynotebook.mappers.SelectedTestOptionMapper;
import com.danny.vocabularynotebook.mappers.TestResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public abstract class TestResultMapperDecorator implements TestResultMapper {
    @Autowired
    @Qualifier("delegate")
    private TestResultMapper delegate;

    @Autowired
    private SelectedTestOptionMapper selectedTestOptionMapper;

    @Override
    public TestResult testResultCreationDTOToTestResult(TestResultCreationDTO testResultCreationDTO) {
        TestResult testResult = delegate.testResultCreationDTOToTestResult(testResultCreationDTO);
        if (Objects.nonNull(testResultCreationDTO.getSelectedTestOptions())) {
            for (SelectedTestOptionCreationDTO selectedTestOptionCreationDTO : testResultCreationDTO.getSelectedTestOptions()) {
                SelectedTestOption selectedTestOption = selectedTestOptionMapper.selectedTestOptionCreationDTOToSelectedTestOption(selectedTestOptionCreationDTO);
                testResult.addSelectedTestOption(selectedTestOption);
            }
        }

        return testResult;
    }
}
