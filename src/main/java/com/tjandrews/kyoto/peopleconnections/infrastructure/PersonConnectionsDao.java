package com.tjandrews.kyoto.peopleconnections.infrastructure;

import java.util.Map;

public interface PersonConnectionsDao {
  public Map<Integer, PersonConnections> getPersonConnections();
}
