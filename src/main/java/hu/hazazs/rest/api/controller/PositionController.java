package hu.hazazs.rest.api.controller;

import hu.hazazs.rest.api.entity.h2.Aircraft;
import hu.hazazs.rest.api.repository.h2.AircraftRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequiredArgsConstructor
public class PositionController {

    @NonNull
    private final AircraftRepository aircraftRepository;
    private final WebClient webClient = WebClient.create("http://localhost:7634/aircraft");

    @GetMapping("/aircraft")
    public String getCurrentAircraftPositions(Model model) {
        aircraftRepository.deleteAll();

        webClient.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(aircraft -> !aircraft.getReg().isEmpty())
                .toStream()
                .forEach(aircraftRepository::save);

        model.addAttribute("currentPositions", aircraftRepository.findAll());

        return "positions";
    }

}