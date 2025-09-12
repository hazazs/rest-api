package hu.hazazs.rest.api.configuration;

import hu.hazazs.rest.api.entity.h2.Aircraft;
import hu.hazazs.rest.api.repository.h2.AircraftRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
public class PositionRetriever {

    private final AircraftRepository aircraftRepository;

    @Bean
    public Consumer<Aircraft> retrievePositions() {
        return aircraft -> {
            aircraftRepository.deleteAll();
            aircraftRepository.save(aircraft);
        };
    }

}