package io.clue2solve.huggingface.inference.spring.cloud.starter.autoconfigure;

import io.clue2solve.huggingface.inference.spring.cloud.starter.config.HuggingFaceProperties;
import io.clue2solve.huggingface.inference.spring.cloud.starter.service.HFInferenceService;
import io.clue2solve.huggingface.inference.spring.cloud.starter.service.impl.HFTextGenerationService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties({ HuggingFaceProperties.class })
public class HFServiceAutoconfig {

	@Bean
	public HFInferenceService hfInferenceService(HuggingFaceProperties properties) {
		return new HFTextGenerationService(properties);
	}

}
