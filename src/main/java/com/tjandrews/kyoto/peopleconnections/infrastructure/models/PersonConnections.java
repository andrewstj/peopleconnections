package com.tjandrews.kyoto.peopleconnections.infrastructure.models;

import java.util.HashSet;
import java.util.Set;

public class PersonConnections {
    private Integer id;
    private Set<Integer> connections = new HashSet<Integer>();


    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Set<Integer> getConnections() {
        return connections;
    }

    public void setConnections(final Set<Integer> connections) {
        this.connections = connections;
    }

    public String toString() {
      return id + " - connections " + connections.size();
    }
}