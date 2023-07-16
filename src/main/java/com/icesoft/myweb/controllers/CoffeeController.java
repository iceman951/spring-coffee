package com.icesoft.myweb.controllers;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.icesoft.myweb.models.Coffee;
import com.icesoft.myweb.repositories.CoffeeRepository;

import lombok.extern.log4j.Log4j2;

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
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Log4j2
@RequestMapping("/coffees")
public class CoffeeController {
    private final CoffeeRepository coffeeRepository;

    public CoffeeController(CoffeeRepository coffeeRepository) {

        this.coffeeRepository = coffeeRepository;

        // this.coffeeRepository.saveAll(List.of(
        // new Coffee("Café Ganador"),
        // new Coffee("Café Lareño"),
        // new Coffee("Café Três Pontas")
        // ));

        // this.coffeeRepository.addAll(List.of(
        // new Coffee("Café Ganador"),
        // new Coffee("Café Lareño"),
        // new Coffee("Café Três Pontas")
        // ));
    }

    @GetMapping("/search")
    Optional<Coffee> getCoffeeBySearch(@RequestParam String name) {
        // Stream<Coffee> = this.coffeeRepository.findCoffeeByname(name);
        return StreamSupport.stream(coffeeRepository.findAll().spliterator(), false).
        filter(coffee -> coffee.getName().equals(name)).findFirst();
    }

    @GetMapping("")
    Iterable<Coffee> getCoffees() {
        log.info("Getting all coffees");
        return this.coffeeRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        log.info("Getting coffee by id");
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

    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
        this.coffeeRepository.save(coffee);
        log.trace("[POST] create new coffee" + coffee.info());
        return coffee;
    }

    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        return (!this.coffeeRepository.existsById(id))
                ? new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED)
                : new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCoffee(@PathVariable String id) {
        this.coffeeRepository.deleteById(id);
        return new ResponseEntity<>("Coffee deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("allMembers")
    ResponseEntity<String> deleteAllCoffees() {
        this.coffeeRepository.deleteAll();
        return new ResponseEntity<>("All coffees deleted successfully", HttpStatus.OK);
    }
}