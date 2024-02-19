package com.danny.vocabularynotebook.configs;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.KeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIClientConfig {
    @Value("${openai.api.key}")
    private String openaiAPIKey;

    @Value("${openai.api.endpoint}")
    private String openaiAPIEndpoint;

    @Bean
    public OpenAIClient createOpenAIClient() {
        return new OpenAIClientBuilder()
                .credential(new KeyCredential(openaiAPIKey))
                .endpoint(openaiAPIEndpoint)
                .buildClient();
    }
}
