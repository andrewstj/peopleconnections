package com.tjandrews.kyoto.peopleconnections.infrastructure.models;

public class Person {
    private Integer id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String toString() {
      return id + " - " + name;
    }
}