package com.icesoft.myweb.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.icesoft.myweb.models.Coffee;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CoffeeController {
    List<Coffee> store = new ArrayList<>();

    public CoffeeController() {
        store.addAll(List.of(
            new Coffee("Café Ganador"),
            new Coffee("Café Lareño"),
            new Coffee("Café Três Pontas")
        ));
    }

    @GetMapping("/coffees")
    Iterable<Coffee> getCoffees() {
        return store;
    }

    @GetMapping("/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        
        for (Coffee c : store) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

    // @GetMapping("/coffees/{name}")
    // Optional<Coffee> getCoffeeByName(@PathVariable String name) {
        
    //     for (Coffee c : store) {
    //         if (c.getName().equals(name)) {
    //             return Optional.of(c);
    //         }
    //     }

    //     return Optional.empty();
    // }

    @PostMapping("/coffees")
    Coffee postCoffee(@RequestBody Coffee coffee) {
        store.add(coffee);
        return coffee;
    }

    @PutMapping("/coffees/{id}")
    Coffee putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        int coffeeIndex = -1;
        for (Coffee c : store) {
            if (c.getId().equals(id)) {
                coffeeIndex = store.indexOf(c);
                store.set(coffeeIndex, coffee);
            }
        }

        return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
    }

    @DeleteMapping("/coffees/{id}")
    void deleteCoffee(@PathVariable String id) {
        store.removeIf(c -> c.getId().equals(id));
    }

}