package hu.hazazs.rest.api.configuration;

import hu.hazazs.rest.api.entity.h2.Aircraft;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.List;
import java.util.function.Supplier;

@Configuration
public class PositionReporter {

    @Bean
    public Supplier<List<Aircraft>> reportPositions() {
        return () -> List.of(getAircraft(), getAircraft());
    }

    private Aircraft getAircraft() {
        return new Aircraft(
                null,
                "SAL001",
                "sqwk",
                "N12345",
                "SAL001",
                "route",
                "LJ",
                "ct",
                30000,
                280,
                440,
                0,
                0,
                39.2979849,
                -94.71921,
                0.0d,
                0.0d,
                0.0d,
                false,
                true,
                Instant.now(),
                Instant.now(),
                Instant.now()
        );
    }

}