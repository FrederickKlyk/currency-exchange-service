package de.fred.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //@CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //RateLimiter: z.B. 10s => 10000 calls to the sample api, man kan verschiedne RateLimits für die jeweiligen API Methoden (siehe name="") in diesem Microservice festlegen
    //@RateLimiter(name = "sample-api")
    @Bulkhead(name = "sample-api")
    public String sampleApi() {
        logger.info("Sample API Call received.");
        /*
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return forEntity.getBody();
        */
        return "sample-api";
    }

    public String hardcodedResponse(Exception e) {
        return "fallback-response";
    }
}
