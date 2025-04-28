package hu.hazazs.rest.api.controller;

import hu.hazazs.rest.api.entity.Coffee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees")
public class RestApiController {

    private final List<Coffee> coffees = new ArrayList<>();

    public RestApiController() {
        coffees.addAll(List.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Três Pontas")
        ));
    }

    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffees;
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable("id") String id) {
        return coffees.stream()
                .filter(coffee -> coffee.getId().equals(id))
                .findFirst();
    }

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable("id") String id, @RequestBody Coffee coffee) {
        int coffeeIndex = -1;

        for (int i = 0; i < coffees.size(); i++) {
            if (coffees.get(i).getId().equals(id)) {
                coffeeIndex = i;
                coffees.set(coffeeIndex, coffee);
            }
        }

        return coffeeIndex == -1 ?
                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable("id") String id) {
        coffees.removeIf(coffee -> coffee.getId().equals(id));
    }

}