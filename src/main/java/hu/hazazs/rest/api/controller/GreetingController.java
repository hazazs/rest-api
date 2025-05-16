package hu.hazazs.rest.api.controller;

import hu.hazazs.rest.api.dto.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final Greeting greeting;

    @Autowired
    public GreetingController(Greeting greeting) {
        this.greeting = greeting;
    }

    @GetMapping
    Optional<String> getGreeting() {
        return Optional.ofNullable(greeting.getName());
    }

    @GetMapping("/coffee")
    Optional<String> getNameAndCoffee() {
        return Optional.ofNullable(greeting.getCoffee());
    }

}