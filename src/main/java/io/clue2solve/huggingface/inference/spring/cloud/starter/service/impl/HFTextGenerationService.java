package io.clue2solve.huggingface.inference.spring.cloud.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.clue2solve.huggingface.inference.spring.cloud.starter.config.HuggingFaceProperties;
import io.clue2solve.huggingface.inference.spring.cloud.starter.service.HFInferenceService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class HFTextGenerationService implements HFInferenceService {

	private final HuggingFaceProperties properties;

	public HFTextGenerationService(HuggingFaceProperties properties) {
		this.properties = properties;
	}

	@Override
	public String invoke(String prompt) throws JsonProcessingException {

		RestTemplate restTemplate = new RestTemplateBuilder().build();

		String url = "https://api-inference.huggingface.co/models/" + properties.modelName();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setBearerAuth(properties.apiToken()); // Replace with your actual token

		String requestBody = "{\"inputs\":\"" + prompt + "\"}";

		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

		return response.getBody();
	}

}
