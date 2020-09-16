package com.tjandrews.kyoto.peopleconnections.infrastructure.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PeopleNetworkGraph {
    private final Map<Integer, PersonConnections> connectionsById = new HashMap<Integer, PersonConnections>();

    public void addBidirectionalPaths(Integer id, Collection<Integer> paths) {
      if (connectionsById.get(id) == null) {
        connectionsById.put(id, new PersonConnections());
      }
      connectionsById.get(id).getConnections().addAll(paths);
      for (Integer connectedId : paths) {
        addDirectedPath(connectedId, id);
      }
    }

    private void addDirectedPath(Integer id, Integer path) {
      if (connectionsById.get(id) == null) {
        connectionsById.put(id, new PersonConnections());
      }
      connectionsById.get(id).getConnections().add(path);
    }

    public PersonConnections getConnectionsForId(Integer id) {
      return connectionsById.get(id);
    }

    public Collection<Integer> getPeopleIds() {
      return connectionsById.keySet();
    }

}