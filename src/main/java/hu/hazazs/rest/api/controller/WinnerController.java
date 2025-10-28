package hu.hazazs.rest.api.controller;

import hu.hazazs.rest.api.entity.mongodb.Winner;
import hu.hazazs.rest.api.repository.mongodb.WinnerRepository;
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
public class WinnerController {

    @NonNull
    private final WinnerRepository winnerRepository;
    private static final String RANDOM_PERSON_API = "http://localhost:3294/random-person";
    private static final int ADULT_AGE = 18;

    @GetMapping("/random-winner")
    public String getRandomWinner(Model model) {
        model.addAttribute("random_winner", Objects.requireNonNull(WebClient.create(RANDOM_PERSON_API)
                .get()
                .retrieve()
                .bodyToFlux(Winner.class)
                .flatMap(winner -> winner.getAge() >= ADULT_AGE ? winnerRepository.save(winner) : Mono.just(winner))
                .collectList()
                .block()));
        return "random-winner";
    }

}