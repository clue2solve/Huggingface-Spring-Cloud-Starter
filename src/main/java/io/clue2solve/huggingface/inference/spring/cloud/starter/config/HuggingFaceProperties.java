package io.clue2solve.huggingface.inference.spring.cloud.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "huggingface")
public record HuggingFaceProperties(String apiToken, String modelName) {

}
