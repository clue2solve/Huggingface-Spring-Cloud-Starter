package io.clue2solve.spring.cloud.starter.huggingface.inference.starter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.clue2solve.spring.cloud.starter.huggingface.inference.autoconfigure.RestTemplateAutoConfiguration;
import io.clue2solve.huggingface.inference.spring.cloud.starter.service.HFModels;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = { TestInit.class, HFModels.class, RestTemplateAutoConfiguration.class })
public class HFModelsTest {

	Logger logger = LoggerFactory.getLogger(HFModelsTest.class);

	@Autowired
	private HFModels hfModels;

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testGetModelsByFilter() throws Exception {
		String response = hfModels.getModelsByFilter("text-classification", "GPT3");

		// Assert that the response is not null
		assertNotNull(response);

		// Parse the response into a JsonNode
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response);

		// Pretty print the JsonNode
		String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
		logger.info("Response: {}", prettyJson);

		// Assert that the JsonNode is an array
		assertTrue(node.isArray());
	}

}