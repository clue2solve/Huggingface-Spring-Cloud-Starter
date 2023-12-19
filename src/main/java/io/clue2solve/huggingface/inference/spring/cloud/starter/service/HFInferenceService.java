package io.clue2solve.huggingface.inference.spring.cloud.starter.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface HFInferenceService {

	/**
	 * Invoke the model
	 * @param prompt String
	 * @return String
	 * @throws JsonProcessingException JsonProcessingException
	 */
	String invoke(String prompt) throws JsonProcessingException;

}
