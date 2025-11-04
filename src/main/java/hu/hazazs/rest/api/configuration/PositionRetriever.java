package hu.hazazs.rest.api.configuration;

import hu.hazazs.rest.api.entity.h2.Aircraft;
import hu.hazazs.rest.api.repository.h2.AircraftRepository;
import hu.hazazs.rest.api.websocket.WebSocketHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
@Slf4j
@SuppressWarnings("unused")
public class PositionRetriever {

    private final AircraftRepository aircraftRepository;
    private final WebSocketHandler webSocketHandler;

    @Bean
    public Consumer<Aircraft> retrievePosition() {
        return aircraft -> {
            aircraftRepository.deleteAll();
            aircraftRepository.save(aircraft);

            sendPosition();
        };
    }

    private void sendPosition() {
        if (aircraftRepository.count() > 0) {
            for (WebSocketSession session : webSocketHandler.getSessionList()) {
                try {
                    session.sendMessage(new TextMessage(aircraftRepository.findAll().toString()));
                } catch (IOException exception) {
                    log.error(STR."Something went wrong during sending the message: \{ exception.getLocalizedMessage() }");
                }
            }
        }
    }

}