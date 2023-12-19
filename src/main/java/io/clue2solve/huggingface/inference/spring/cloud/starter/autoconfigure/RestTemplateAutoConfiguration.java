package io.clue2solve.huggingface.inference.spring.cloud.starter.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateAutoConfiguration {

	@Bean
	public RestTemplate restTemplate() {
		// You can customize the RestTemplate here if needed
		return new RestTemplate();
	}

}
