package com.tjandrews.kyoto.peopleconnections.infrastructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class PersonFileDao implements PersonDao {
  final Logger LOGGER = LoggerFactory.getLogger(getClass());

  public List<Person> getPeople() {
    List<Person> people = new ArrayList<Person>();
    try {
      File file = ResourceUtils.getFile("classpath:Person.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = reader.readLine();
      while (line != null) {
        Person person = new Person();
        String[] splitData = line.split("\\s+");
        person.setId(Integer.parseInt(splitData[0]));
        person.setName(splitData[1]);
        LOGGER.info(person.toString());
        people.add(person);
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      LOGGER.error("IOException", e);
    }
    return people;
  }
}
