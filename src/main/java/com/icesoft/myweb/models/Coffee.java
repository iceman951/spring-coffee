package com.icesoft.myweb.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Coffee {
    @Id
    private String id;
    private String name;

    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Coffee(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public Coffee(int name) {
        this(UUID.randomUUID().toString(), String.valueOf(name));
    }

    public Coffee() {

    }

    public String info() {
        return "Coffee [id=" + id + ", name=" + name + "]";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coffee setName(String name) {
        this.name = name;
        return this;
    }

}
