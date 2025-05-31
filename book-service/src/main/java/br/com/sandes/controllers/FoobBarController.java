package br.com.sandes.controllers;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Foo bar")
@RestController
@RequestMapping("book-service")
public class FoobBarController {

    private final Logger logger = LoggerFactory.getLogger(FoobBarController.class);

    @GetMapping("/foo-bar")
    @Operation(summary = "Foo bar")
    //@Retry(name = "default", fallbackMethod = "fallBackMethod")
    //@CircuitBreaker(name = "default", fallbackMethod = "fallBackMethod")
    //@RateLimiter(name = "default") define uma quantidade de requisicoes que o endpoint anotado pode receber
    @Bulkhead(name = "default")
    public String fooBar() {
        logger.info("Request to foo-bar is received!");
//        var response = new RestTemplate().getForEntity(
//                "http://localhost:8080/foo-bar", String.class);
        return "Foo-Bar!!!";
    }

    public String fallBackMethod(Exception ex) {
        return "fallBackMethod foo-bar!!!";
    }
}
