package com.tjandrews.kyoto.peopleconnections.infrastructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class PersonConnectionsFileDao implements PersonConnectionsDao {
  final Logger LOGGER = LoggerFactory.getLogger(getClass());

  @Override
  public Collection<PersonConnections> getPersonConnections() {
    Collection<PersonConnections> connections = new ArrayList<PersonConnections>();
    try {
      File file = ResourceUtils.getFile("classpath:Relationship.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = reader.readLine();
      while (line != null) {
        String[] splitData = line.split(":");
        Integer id = Integer.parseInt(splitData[0].trim());
        List<String> connectionList = Arrays.asList(splitData[1].trim().split("\\s*,\\s*"));
        PersonConnections personConnections = new PersonConnections();
        personConnections.setId(id);
        personConnections.setConnections(connectionList.stream().map(Integer::parseInt).collect(Collectors.toList()));
        connections.add(personConnections);
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      LOGGER.error("IOException", e);
    }
    return connections;
  }
  
}
