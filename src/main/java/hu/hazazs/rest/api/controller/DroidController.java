package hu.hazazs.rest.api.controller;

import hu.hazazs.rest.api.dto.Droid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/droid")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@SuppressWarnings("unused")
public class DroidController {

    private final Droid droid;

    @GetMapping
    Droid getDroid() {
        return droid;
    }

}