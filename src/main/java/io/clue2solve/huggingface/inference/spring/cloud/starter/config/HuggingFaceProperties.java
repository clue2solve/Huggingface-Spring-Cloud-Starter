package io.clue2solve.huggingface.inference.spring.cloud.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "huggingface")
public record HuggingFaceProperties(
    String apiToken, 
    String modelName, 
    Integer topK, 
    Float topP, 
    Float temperature, 
    Float repetitionPenalty, 
    Integer maxNewTokens, 
    Float maxTime, 
    Boolean returnFullText, 
    Integer numReturnSequences, 
    Boolean doSample, 
    Options options) {

    public HuggingFaceProperties {
        topK = (topK != null) ? topK : 0; // Default value
        topP = (topP != null) ? topP : 0.0f; // Default value
        temperature = (temperature != null) ? temperature : 1.0f; // Default value
        repetitionPenalty = (repetitionPenalty != null) ? repetitionPenalty : 1.0f; // Default value
        maxNewTokens = (maxNewTokens != null) ? maxNewTokens : 50; // Default value
        maxTime = (maxTime != null) ? maxTime : 60.0f; // Default value
        returnFullText = (returnFullText != null) ? returnFullText : true; // Default value
        numReturnSequences = (numReturnSequences != null) ? numReturnSequences : 1; // Default value
        doSample = (doSample != null) ? doSample : true; // Default value
        options = (options != null) ? options : new Options(true, false); // Default value
    }

    public static record Options(Boolean useCache, Boolean waitForModel) {
        public Options {
            useCache = (useCache != null) ? useCache : true; // Default value
            waitForModel = (waitForModel != null) ? waitForModel : false; // Default value
        }
    }
}