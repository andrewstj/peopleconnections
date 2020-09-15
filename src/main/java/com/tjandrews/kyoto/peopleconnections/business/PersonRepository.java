package com.tjandrews.kyoto.peopleconnections.business;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.tjandrews.kyoto.peopleconnections.infrastructure.PersonDao;
import com.tjandrews.kyoto.peopleconnections.infrastructure.models.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonRepository {
  final Logger LOGGER = LoggerFactory.getLogger(getClass());
  private PersonDao personDao;

  private Map<Integer, Person> peopleById = new HashMap<Integer, Person>();

  @Autowired
  public PersonRepository(PersonDao personDao) {
    this.personDao = personDao;
  }

  @PostConstruct
  public void initializeData() {
    peopleById = personDao.getPeople().stream().collect(Collectors.toMap(Person::getId, person -> person));
  }

  public Optional<Person> getPerson(Integer id) {
    return Optional.ofNullable(peopleById.get(id));
  }

  public Collection<Person> getAllPeople() {
    return peopleById.values();
  }

  public Collection<Person> getPeopleFromIds(Collection<Integer> ids) {
    return ids.stream().map(id -> peopleById.get(id)).collect(Collectors.toList());
  }
}
