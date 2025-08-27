package hu.hazazs.rest.api.entity.postgres;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Coffee {

    @Id
    private String id;
    private String name;

}