# Spring Cloud Huggingface Starter
## Lets you use the Huggingface model inference endpoints with Spring Boot and Spring Cloud

[![Alpha](https://img.shields.io/badge/Release-Alpha-darkred)](https://img.shields.io/badge/Release-Alpha-darkred) ![Github Action CI Workflow Status](https://github.com/clue2solve/Huggingface-Spring-Cloud-Starter/actions/workflows/ci.yml/badge.svg) [![Known Vulnerabilities](https://snyk.io/test/github/clue2solve/Huggingface-Spring-Cloud-Starter/badge.svg?style=plastic)](https://snyk.io/test/github/clue2solve/Huggingface-Spring-Cloud-Starter) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

### Huggingface 
This project provides a developer-friendly way to access the services of [Huggingface](https://huggingface.co/), a platform that provides a large number of pre-trained models for Natural Language Processing (NLP). This is a [Spring Cloud](https://spring.io/projects/spring-cloud) Starter for the Huggingface service that removes all the boilerplate code required to access the service. It uses the standards of Spring Boot and Spring Cloud to provide the configuration and the auto-wiring of the service objects.

#### This project leverages Spring Cloud for credential management 

Just provide the right properties as defined below and the appropriate Huggingface API token will be activated for injection into your Spring objects. 

```properties
# for Huggingface API token
huggingface.apiToken=YOUR_API_TOKEN
```

#### Properties that will enable HuggingfaceService
```properties
huggingface.modelName=gpt3
huggingface.topK=5
huggingface.topP=0.5
huggingface.temperature=0.5
huggingface.repetitionPenalty=1.0
huggingface.maxNewTokens=100
huggingface.maxTime=60.0
huggingface.returnFullText=true
huggingface.numReturnSequences=1
huggingface.doSample=true
huggingface.options.useCache=true
huggingface.options.waitForModel=false
```

#### The HuggingfaceService
Once the right API token is injected, you can expect the activation of the HuggingfaceService.

One can use the activated service to interact with the Huggingface model inference endpoints based on the docs for the Huggingface Service.

#### Usage
Once activated, a Service can be autowired and used as below.

```Java
@RestController
public class HuggingfaceController {

   private final HuggingfaceService huggingfaceService;


   @Autowired
   public HuggingfaceController(HuggingfaceService huggingfaceService) {
      this.huggingfaceService = huggingfaceService;
   }

   @GetMapping("/invoke")
   public String invoke(@RequestParam String prompt) {
      try {
         return huggingfaceService.invoke(prompt);
      } catch (Exception e) {
         return "Error invoking Huggingface: " + e.getMessage();
      }
   }
}
```

You might also want to take a look at and/or run the Service tests. To do that, make sure you've set the appropriate HUGGINGFACE_API_TOKEN environment variable, then execute

```bash
mvn test -Dspring.profiles.active=authorized
```
