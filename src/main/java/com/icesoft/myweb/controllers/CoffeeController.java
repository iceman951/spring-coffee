package com.icesoft.myweb.controllers;
import java.util.List;
import java.util.Optional;

import com.icesoft.myweb.models.Coffee;
// import com.icesoft.myweb.models.CoffeeRepository;
import com.icesoft.myweb.models.CoffeeRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private final CoffeeRepository coffeeRepository;

    public CoffeeController(CoffeeRepository coffeeRepository) {

        this.coffeeRepository = coffeeRepository;

        this.coffeeRepository.saveAll(List.of(
            new Coffee("Café Ganador"),
            new Coffee("Café Lareño"),
            new Coffee("Café Três Pontas")
        ));

        // this.coffeeRepository.addAll(List.of(
        //     new Coffee("Café Ganador"),
        //     new Coffee("Café Lareño"),
        //     new Coffee("Café Três Pontas")
        // ));
    }

    @GetMapping("")
    Iterable<Coffee> getCoffees() {
        return this.coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        return this.coffeeRepository.findById(id);
    }

    @GetMapping("/name/{name}")
    Optional<Coffee> getCoffeeByName(@PathVariable String name) {
        
        for (Coffee c : this.coffeeRepository.findAll()) {
            if (c.getName().equals(name)) {
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    @PostMapping("")
    Coffee postCoffee(@RequestBody Coffee coffee) {
        this.coffeeRepository.save(coffee);
        return coffee;
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        return (!this.coffeeRepository.existsById(id)) ?
            new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED) :
            new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCoffee(@PathVariable String id) {
        this.coffeeRepository.deleteById(id);
        return new ResponseEntity<>("Coffee deleted successfully", HttpStatus.OK);
    }

}