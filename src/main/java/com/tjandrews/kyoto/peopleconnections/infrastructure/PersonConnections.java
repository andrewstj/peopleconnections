package com.tjandrews.kyoto.peopleconnections.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class PersonConnections {
    private Integer id;
    private List<Integer> connections = new ArrayList<Integer>();


    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public List<Integer> getConnections() {
        return connections;
    }

    public void setConnections(final List<Integer> connections) {
        this.connections = connections;
    }

    public String toString() {
      return id + " - connections " + connections.size();
    }
}