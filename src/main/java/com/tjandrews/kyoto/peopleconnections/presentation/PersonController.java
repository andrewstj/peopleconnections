package com.tjandrews.kyoto.peopleconnections.presentation;

import java.util.Collection;
import java.util.Optional;

import com.tjandrews.kyoto.peopleconnections.business.PersonConnectionsRepository;
import com.tjandrews.kyoto.peopleconnections.business.PersonRepository;
import com.tjandrews.kyoto.peopleconnections.infrastructure.models.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
  private final PersonRepository personRepository;
  private final PersonConnectionsRepository personConnectionsRepository;

  @Autowired
  public PersonController(PersonRepository personRepository, PersonConnectionsRepository personConnectionsRepository) {
    this.personRepository = personRepository;
    this.personConnectionsRepository = personConnectionsRepository;
  }

  @GetMapping("/people")
  Collection<Person> all() {
    return personRepository.getAllPeople();
  }

  @GetMapping("/people/{id}")
  Person one(@PathVariable final Integer id) {
    return personRepository.getPerson(id)
      .orElseThrow(() -> new PersonNotFoundException(id));
  }

  @GetMapping("/people/{id}/connections")
  Collection<Person> personConnections(@PathVariable final Integer id, 
  @RequestParam(required=false) Integer degree) {
    return personConnectionsRepository.getConnectionsToPerson(id, Optional.ofNullable(degree))
      .orElseThrow(() -> new PersonNotFoundException(id));
  }

  @GetMapping("/people/{id}/connectionSize")
  Integer personConnectionSize(@PathVariable final Integer id, 
  @RequestParam(required=false) Integer degree) {
    return personConnectionsRepository.getConnectionsToPerson(id, Optional.ofNullable(degree))
      .orElseThrow(() -> new PersonNotFoundException(id)).size();
  }

  @GetMapping("/connections/_queryCommonConnections")
  Collection<Person> queryCommonConnections(
  @RequestParam(required=true) Integer firstPerson,
  @RequestParam(required=true) Integer secondPerson,
  @RequestParam(required=false) Integer degree) {
    return personConnectionsRepository.getCommonConnections(firstPerson, secondPerson, Optional.ofNullable(degree))
      .orElseThrow(() -> new PersonNotFoundException());
  }
  
  @GetMapping("/connections/_queryLeastConnections")
  Person queryLeastConnections(@RequestParam(required=false) Integer degree) {
    return personConnectionsRepository.getLeastConnections(Optional.ofNullable(degree));
  }

  @GetMapping("/connections/_queryMostConnections")
  Person queryMostConnections(@RequestParam(required=false) Integer degree) {
    return personConnectionsRepository.getMostConnections(Optional.ofNullable(degree));
  }
}
