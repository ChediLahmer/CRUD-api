package com.example.my_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person {
    private final UUID id;

    private final String name;


    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name ) {
        this.name = name;
        this.id = id;
    }
    public UUID getId(){return id;}
    public String getName(){return name;}


}
