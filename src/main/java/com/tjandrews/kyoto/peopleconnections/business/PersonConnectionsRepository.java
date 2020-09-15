package com.tjandrews.kyoto.peopleconnections.business;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.tjandrews.kyoto.peopleconnections.infrastructure.Person;
import com.tjandrews.kyoto.peopleconnections.infrastructure.PersonConnections;
import com.tjandrews.kyoto.peopleconnections.infrastructure.PersonConnectionsDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: Add tests.

@Component
public class PersonConnectionsRepository {
  final Logger LOGGER = LoggerFactory.getLogger(getClass());
  private final PersonConnectionsDao personConnectionsDao;
  private final PersonRepository personRepository;

  private Map<Integer, PersonConnections> connectionsById = new HashMap<Integer, PersonConnections>();

  @Autowired
  public PersonConnectionsRepository(PersonConnectionsDao personConnectionsDao, PersonRepository personRepository) {
    this.personConnectionsDao = personConnectionsDao;
    this.personRepository = personRepository;
  }

  @PostConstruct
  public void initializeData() {
    connectionsById = personConnectionsDao.getPersonConnections();
    LOGGER.info("Initialized connections " + connectionsById.size());
  }

  public Optional<Integer> getNumberOfConnectionsToPerson(Integer id, Optional<Integer> depth) {
    return getConnectionsToPerson(id, depth).map(connections -> connections.size());
  }

  public Optional<Collection<Person>> getConnectionsToPerson(Integer id, Optional<Integer> depth) {
    PersonConnections connection = this.connectionsById.get(id);
    if (connection == null) {
      return Optional.empty();
    } else if (!depth.isPresent() || depth.get() <= 1) {
      return Optional.of(personRepository.getPeopleFromIds(connection.getConnections()));
    } else {
      return Optional.of(findConnectionsByDepth(id, depth.get()));
    }
  }

  private Collection<Person> findConnectionsByDepth(Integer personId, Integer depth) {
    Set<Integer> currentConnections = new HashSet<Integer>();
    findConnectionsByDepth(currentConnections, personId, depth);
    currentConnections.remove(personId);
    return personRepository.getPeopleFromIds(currentConnections);
  }

  private void findConnectionsByDepth(Set<Integer> currentConnections, Integer personId, Integer depth) {
    if (depth == 0) {
      return;
    }
    Set<Integer> currentPersonConnections = connectionsById.get(personId).getConnections();
    currentConnections.addAll(currentPersonConnections);
    currentPersonConnections
        .forEach(connectionPersonId -> findConnectionsByDepth(currentConnections, connectionPersonId, depth - 1));
  }

  public Optional<Collection<Person>> getCommonConnections(Integer firstPerson, Integer secondPerson,
      Optional<Integer> depth) {

    Optional<Collection<Person>> firstPersonConnections = getConnectionsToPerson(firstPerson, depth);
    Optional<Collection<Person>> secondPersonConnections = getConnectionsToPerson(secondPerson, depth);
    if (!firstPersonConnections.isPresent() || !secondPersonConnections.isPresent()) {
      return Optional.empty();
    }
    firstPersonConnections.get().retainAll(secondPersonConnections.get());
    return firstPersonConnections;
  }

  public Person getLeastConnections(Optional<Integer> depth) {
    int leastConnection = Integer.MAX_VALUE;
    int personWithLeast = 0;
    for (int id : connectionsById.keySet()) {
      int numberOfConnections = getNumberOfConnectionsToPerson(id, depth).get();
      if (numberOfConnections < leastConnection) {
        leastConnection = numberOfConnections;
        personWithLeast = id;
        LOGGER.info("Person with least is now " + id + " with: " + leastConnection);
      }
    }
    return personRepository.getPerson(personWithLeast).get();
  }

  public Person getMostConnections(Optional<Integer> depth) {
    int mostConnection = 0;
    int personWithMost = 0;
    for (int id : connectionsById.keySet()) {
      int numberOfConnections = getNumberOfConnectionsToPerson(id, depth).get();
      if (numberOfConnections > mostConnection) {
        mostConnection = numberOfConnections;
        personWithMost = id;
        LOGGER.info("Person with most is now " + id + " with: " + mostConnection);
      }
    }
    return personRepository.getPerson(personWithMost).get();
  }

}
