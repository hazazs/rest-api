package hu.hazazs.rest.api.controller;

import hu.hazazs.rest.api.entity.mongodb.Person;
import hu.hazazs.rest.api.repository.mongodb.PersonRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class PersonController {

    @NonNull
    private final PersonRepository personRepository;
    private static final String PERSON_API = "http://localhost:3294/random-person";
    private static final int ADULT_AGE = 18;

    @GetMapping("/random-winner")
    public String getPeople(Model model) {
        model.addAttribute("people", Objects.requireNonNull(WebClient.create(PERSON_API)
                .get()
                .retrieve()
                .bodyToFlux(Person.class)
                .flatMap(person -> person.getAge() >= ADULT_AGE ? personRepository.save(person) : Mono.just(person))
                .collectList()
                .block()));
        return "people";
    }

}