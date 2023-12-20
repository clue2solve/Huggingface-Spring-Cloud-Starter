package io.clue2solve.huggingface.inference.spring.cloud.starter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.clue2solve.huggingface.inference.spring.cloud.starter.config.HuggingFaceProperties;
import io.clue2solve.huggingface.inference.spring.cloud.starter.service.HFInferenceService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
		headers.setBearerAuth(properties.apiToken());

		Map<String, Object> requestBodyMap = new HashMap<>();
		requestBodyMap.put("inputs", prompt);
		if (properties.topK() != null) requestBodyMap.put("top_k", properties.topK());
		if (properties.topP() != null) requestBodyMap.put("top_p", properties.topP());
		if (properties.temperature() != null) requestBodyMap.put("temperature", properties.temperature());
		if (properties.repetitionPenalty() != null) requestBodyMap.put("repetition_penalty", properties.repetitionPenalty());
		if (properties.maxNewTokens() != null) requestBodyMap.put("max_new_tokens", properties.maxNewTokens());
		if (properties.maxTime() != null) requestBodyMap.put("max_time", properties.maxTime());
		if (properties.returnFullText() != null) requestBodyMap.put("return_full_text", properties.returnFullText());
		if (properties.numReturnSequences() != null) requestBodyMap.put("num_return_sequences", properties.numReturnSequences());
		if (properties.doSample() != null) requestBodyMap.put("do_sample", properties.doSample());
		if (properties.options() != null) {
			if (properties.options().useCache() != null) requestBodyMap.put("use_cache", properties.options().useCache());
			if (properties.options().waitForModel() != null) requestBodyMap.put("wait_for_model", properties.options().waitForModel());
		}

		ObjectMapper objectMapper = new ObjectMapper();
		String requestBody = objectMapper.writeValueAsString(requestBodyMap);

		HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

		return response.getBody();
	}

}
