package com.tjandrews.kyoto.peopleconnections.infrastructure;

import java.util.List;

import com.tjandrews.kyoto.peopleconnections.infrastructure.models.Person;

public interface PersonDao {
  public List<Person> getPeople();
}
