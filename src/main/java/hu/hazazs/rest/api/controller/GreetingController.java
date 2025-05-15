package hu.hazazs.rest.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Value("${greeting-name:Mirage}")
    private String name;

    @Value("${greeting-coffee:${greeting-name} is drinking Café Ganador}")
    private String coffee;

    @GetMapping
    Optional<String> getGreeting() {
        return Optional.ofNullable(name);
    }

    @GetMapping("/coffee")
    Optional<String> getNameAndCoffee() {
        return Optional.ofNullable(coffee);
    }

}