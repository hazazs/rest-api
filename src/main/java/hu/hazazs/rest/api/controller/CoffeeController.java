package hu.hazazs.rest.api.controller;

import hu.hazazs.rest.api.entity.postgres.Coffee;
import hu.hazazs.rest.api.repository.postgres.CoffeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/coffees")
@SuppressWarnings("unused")
public class CoffeeController {

    private final CoffeeRepository coffeeRepository;

    @Autowired
    CoffeeController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @GetMapping
    Iterable<Coffee> getCoffees() {
        return coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable("id") String id) {
        return coffeeRepository.findById(id);
    }

    @PostMapping
    Coffee postCoffee(@RequestBody @Valid Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable("id") String id, @RequestBody Coffee coffee) {
        return new ResponseEntity<>(coffeeRepository.save(coffee), coffeeRepository.existsById(id) ?
                HttpStatus.OK :
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable("id") String id) {
        coffeeRepository.deleteById(id);
    }

}