package com.icesoft.myweb.repositories;

import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import com.icesoft.myweb.models.Coffee;

public interface CoffeeRepository extends CrudRepository<Coffee, String> {

    Stream<Coffee> findCoffeeByname(String name);

}