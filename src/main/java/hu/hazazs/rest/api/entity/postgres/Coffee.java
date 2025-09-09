package hu.hazazs.rest.api.entity.postgres;

import hu.hazazs.rest.api.validation.ValidCoffee;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ValidCoffee(idPattern = "^[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}$", namePrefix = "Café ")
public class Coffee {

    @Id
    private String id;
    private String name;

}