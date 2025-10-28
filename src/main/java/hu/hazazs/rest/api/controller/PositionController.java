package hu.hazazs.rest.api.controller;

import hu.hazazs.rest.api.repository.h2.AircraftRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class PositionController {

    @NonNull
    private final AircraftRepository aircraftRepository;

    @GetMapping("/aircraft")
    public String getCurrentAircraftPositions(Model model) {
        model.addAttribute("currentPositions", aircraftRepository.findAll());
        return "positions";
    }

}