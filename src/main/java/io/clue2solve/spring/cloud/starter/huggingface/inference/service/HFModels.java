package io.clue2solve.spring.cloud.starter.huggingface.inference.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class HFModels {

	private final RestTemplate restTemplate;

	public HFModels(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getModelsByFilter(String modelType, String modelId) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://huggingface.co/api/models")
				.queryParam("pipeline_tag", modelType)
				.queryParam("search", modelId);

			return restTemplate.getForObject(builder.toUriString(), String.class);
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Error fetching models: " + e.getMessage();
		}
	}

}
