package com.tjandrews.kyoto.peopleconnections.infrastructure;

import java.util.Map;

import com.tjandrews.kyoto.peopleconnections.infrastructure.models.PersonConnections;

public interface PersonConnectionsDao {
  public Map<Integer, PersonConnections> getPersonConnections();
}
