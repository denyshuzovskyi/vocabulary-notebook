package com.danny.vocabularynotebook.services;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestSystemMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.azure.ai.openai.models.ChatResponseMessage;
import com.azure.ai.openai.models.CompletionsUsage;
import com.danny.vocabularynotebook.dtos.TestCollectionGenerationJobViewDTO;
import com.danny.vocabularynotebook.dtos.TestOptionGeneratedDTO;
import com.danny.vocabularynotebook.dtos.TestOptionsGeneratedDTO;
import com.danny.vocabularynotebook.dtos.WordViewForTestCreationDTO;
import com.danny.vocabularynotebook.entities.Notebook;
import com.danny.vocabularynotebook.entities.Test;
import com.danny.vocabularynotebook.entities.TestCollection;
import com.danny.vocabularynotebook.entities.TestCollectionGenerationJob;
import com.danny.vocabularynotebook.entities.TestOption;
import com.danny.vocabularynotebook.entities.Word;
import com.danny.vocabularynotebook.mappers.TestCollectionGenerationJobMapper;
import com.danny.vocabularynotebook.mappers.TestOptionMapper;
import com.danny.vocabularynotebook.mappers.WordMapper;
import com.danny.vocabularynotebook.repositories.TestCollectionGenerationJobRepository;
import com.danny.vocabularynotebook.repositories.TestCollectionRepository;
import com.danny.vocabularynotebook.repositories.TestRepository;
import com.danny.vocabularynotebook.repositories.WordRepository;
import com.danny.vocabularynotebook.services.jobs.TestCollectionGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestGenerationService {
    private final OpenAIClient openaiClient;
    private final WordRepository wordRepository;
    private final TestCollectionRepository testCollectionRepository;
    private final TestRepository testRepository;
    private final WordMapper wordMapper;
    private final TestOptionMapper testOptionMapper;

    private final TestCollectionGenerator testCollectionGenerator;
    private final TestCollectionGenerationJobRepository testCollectionGenerationJobRepository;
    private final TestCollectionGenerationJobMapper testCollectionGenerationJobMapper;

    @Value("${openai.api.deployment-model-id}")
    private String deploymentOrModelId;

    public void generateTestsForNotebook(Long notebookId) {
        List<Word> words = wordRepository.findAllByNotebookId(notebookId);

        if (words.isEmpty()) {
            throw new IllegalStateException("No words to generate tests for");
        }

        TestCollection testCollection = new TestCollection();
        Notebook notebook = new Notebook();
        notebook.setId(notebookId);
        testCollection.setNotebook(notebook);
        testCollectionRepository.save(testCollection);

        for (Word word : words) {
            WordViewForTestCreationDTO wordViewForTestCreationDTO = wordMapper.wordToWordViewForTestCreationDTO(word);
            ObjectMapper objectMapper = new ObjectMapper();
            String stringRepresentationOfWord;
            try {
                stringRepresentationOfWord = objectMapper.writeValueAsString(wordViewForTestCreationDTO);
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("Unable serialize word object to json string", e);
            }

            List<ChatRequestMessage> chatMessages = new ArrayList<>();
            chatMessages.add(new ChatRequestSystemMessage("You are an api endpoint that takes json as input and outputs json. You return test options for vocabulary learners. Given a word and its definition in format: {\"word\":\"string\",\"definition\":\"string\"}, use the original definition as the correct option (don't include the defining word in it and in other options) and add two incorrect options (one close to the correct definition and one not). Return the result in the format: {\"options\":[{\"definition\":\"string\",\"isCorrect\":\"boolean\"}]}"));
            chatMessages.add(new ChatRequestUserMessage(stringRepresentationOfWord));

            ChatCompletions chatCompletions = openaiClient.getChatCompletions(deploymentOrModelId, new ChatCompletionsOptions(chatMessages));

            System.out.printf("Model ID=%s is created at %s.%n", chatCompletions.getId(), chatCompletions.getCreatedAt());
            System.out.println();
            CompletionsUsage usage = chatCompletions.getUsage();
            System.out.printf("Usage: number of prompt token is %d, "
                            + "number of completion token is %d, and number of total tokens in request and response is %d.%n",
                    usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());

            if( chatCompletions.getChoices().size() != 1) {
                throw new IllegalStateException("Got more than one choice, unable to create result");
            }

            ChatChoice choice = chatCompletions.getChoices().get(0);
            ChatResponseMessage message = choice.getMessage();

            System.out.printf("Index: %d, Chat Role: %s.%n", choice.getIndex(), message.getRole());
            System.out.println("Message:");
            System.out.println(message.getContent());

            TestOptionsGeneratedDTO generatedTestOptions;
            try {
                generatedTestOptions = objectMapper.readValue(message.getContent(), TestOptionsGeneratedDTO.class);
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("Unable to deserialize TestOptionsDTO", e);
            }

            System.out.println("TestOptionsDTO generatedTestOptions: " + generatedTestOptions);
            if (Objects.isNull(generatedTestOptions.getOptions()) || generatedTestOptions.getOptions().length == 0) {
                throw new IllegalStateException("No test options to save");
            }

            Test test = new Test();
            test.setTask(String.format("Choose a correct definition for the word: \"%s\"", word.getWord()));
            test.setWord(word);
            test.setTestCollection(testCollection);

            for(TestOptionGeneratedDTO testOptionGeneratedDTO : generatedTestOptions.getOptions()) {
                TestOption testOption = testOptionMapper.testOptionGeneratedDTOToTestOption(testOptionGeneratedDTO);
                test.addTestOption(testOption);
            }

            testRepository.save(test);
        }
    }

    public TestCollectionGenerationJobViewDTO triggerTestCollectionGenerationForNotebook(Long notebookId) {
        List<Word> words = wordRepository.findAllByNotebookId(notebookId);

        if (words.isEmpty()) {
            throw new IllegalStateException("No words to generate tests for");
        }

        TestCollection testCollection = new TestCollection();
        Notebook notebook = new Notebook();
        notebook.setId(notebookId);
        testCollection.setNotebook(notebook);
        testCollectionRepository.save(testCollection);

        TestCollectionGenerationJob testCollectionGenerationJob = new TestCollectionGenerationJob();
        testCollectionGenerationJob.setTestCollection(testCollection);
        testCollectionGenerationJob.setStatus(TestCollectionGenerationJob.JobStatus.PENDING);
        testCollectionGenerationJobRepository.save(testCollectionGenerationJob);

        testCollectionGenerator.generateTestsForWords(testCollection, words, testCollectionGenerationJob);

        return testCollectionGenerationJobMapper.testCollectionGenerationJobToTestCollectionGenerationJobViewDTO(testCollectionGenerationJob);
    }

}
