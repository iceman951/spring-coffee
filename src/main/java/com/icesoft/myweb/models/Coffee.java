package com.icesoft.myweb.models;

import java.util.UUID;

public class Coffee {
    private final String id;
    private String name;

    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Coffee(String name) {
        this(UUID.randomUUID().toString(), name);
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
