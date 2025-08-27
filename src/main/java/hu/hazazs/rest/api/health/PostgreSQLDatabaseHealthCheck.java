package hu.hazazs.rest.api.health;

import hu.hazazs.rest.api.repository.postgres.CoffeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import static org.springframework.boot.actuate.health.Health.down;
import static org.springframework.boot.actuate.health.Health.up;

/**
 * Extends the /actuator/health endpoint with one additional health check component.
 */
@Component("postgreSQLDatabase")
@AllArgsConstructor
public class PostgreSQLDatabaseHealthCheck implements HealthIndicator {

    private final CoffeeRepository coffeeRepository;

    /**
     * Checks whether at least one Coffee record is populated in the PostgreSQL database.
     *
     * @return {@link Health} with the correspondent UP or DOWN status
     */
    @Override
    public Health health() {
        return (coffeeRepository.findAll().iterator().hasNext()
                    ? up().withDetail("message", "There is at least one Coffee record populated.")
                    : down().withDetail("message", "The coffee table is empty."))
                .build();
    }

}