package hu.hazazs.rest.api.controller;

import hu.hazazs.rest.api.entity.mongodb.Winner;
import hu.hazazs.rest.api.repository.mongodb.WinnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Controller
@SuppressWarnings("unused")
public class WinnerController {

    private final WinnerRepository winnerRepository;
    private final RSocketRequester rSocketRequester;
    private static final String RANDOM_PERSON_API = "http://localhost:3294/random-person";
    private static final Integer RANDOM_PERSON_RSOCKET_PORT = 3293;
    private static final int ADULT_AGE = 18;

    @Autowired
    public WinnerController(WinnerRepository winnerRepository, RSocketRequester.Builder builder) {
        this.winnerRepository = winnerRepository;
        this.rSocketRequester = builder.tcp("localhost", RANDOM_PERSON_RSOCKET_PORT);
    }

    @GetMapping("/random-winner")
    public Mono<String> getRandomWinner(Model model) {
        return WebClient.create(RANDOM_PERSON_API)
                .get()
                .retrieve()
                .bodyToMono(Winner.class)
                .flatMap(winner -> winner.getAge() >= ADULT_AGE ? winnerRepository.save(winner) : Mono.just(winner))
                .doOnNext(winner -> model.addAttribute("random_winner", winner))
                .thenReturn("random-winner");
    }

    /**
     * We need Flux<ServerSentEvent<Winner>> instead of Flux<Winner> here in order to inject {@link StandardCharsets#UTF_8}.
     * Otherwise, browser couldn't encode the characters properly:
     *   á → Ã¡
     *   ó → Ã³
     */
    @ResponseBody
    @GetMapping("/random-winners")
    public ResponseEntity<Flux<ServerSentEvent<Winner>>> getRandomWinners() {
        return ResponseEntity
                .ok()
                .contentType(new MediaType("text", "event-stream", StandardCharsets.UTF_8))
                .body(rSocketRequester
                        .route("random-people")
                        .retrieveFlux(Winner.class)
                        .map(winner -> ServerSentEvent.builder(winner).build()));
    }

}