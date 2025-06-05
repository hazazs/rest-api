package hu.hazazs.rest.api.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "droid")
public class Droid {

    private String id;
    private String description;

}