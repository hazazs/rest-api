package hu.hazazs.rest.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "greeting")
public class Greeting {

    private String name;
    private String coffee;

}