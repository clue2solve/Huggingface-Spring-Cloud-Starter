package io.clue2solve.spring.cloud.starter.huggingface.inference.starter.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.clue2solve.huggingface.inference.spring.cloud.starter.autoconfigure.HFServiceAutoconfig;
import io.clue2solve.huggingface.inference.spring.cloud.starter.service.HFInferenceService;
import io.clue2solve.spring.cloud.starter.huggingface.inference.starter.TestInit;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = { TestInit.class, HFServiceAutoconfig.class })
public class HFTextGenerationServiceTest {

	Logger logger = LoggerFactory.getLogger(HFTextGenerationServiceTest.class);

	@Autowired
	private HFInferenceService hfInferenceService;

	@Test
	public void HFInferenceEndpointServiceTestInvoke() throws Exception {
		String response = hfInferenceService.invoke("the world of technology is heading towards");

		// Assert that the response is not null
		assertNotNull(response);

		logger.info(response);
		// // Parse the response into a JsonNode
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response);

		// // Pretty print the JsonNode
		String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
		logger.info("Response: {}", prettyJson);

		// // Assert that the JsonNode is an array
		assertTrue(node.isArray());
	}

}
