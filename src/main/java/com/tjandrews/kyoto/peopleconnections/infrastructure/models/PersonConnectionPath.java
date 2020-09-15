package com.tjandrews.kyoto.peopleconnections.infrastructure.models;

import java.util.List;

public class PersonConnectionPath {
    private final List<Integer> pathIds;

    public PersonConnectionPath(List<Integer> pathIds) {
        this.pathIds = pathIds;
    }

    public List<Integer> getPathIds() {
        return pathIds;
    }
    
}