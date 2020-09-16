package com.tjandrews.kyoto.peopleconnections.infrastructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.tjandrews.kyoto.peopleconnections.infrastructure.models.PeopleNetworkGraph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class PeopleNetworkGraphFileDao implements PeopleNetworkGraphDao {
  final Logger LOGGER = LoggerFactory.getLogger(getClass());

  @Override
  public PeopleNetworkGraph getPeopleNetworkGraph() {
    PeopleNetworkGraph peopleNetworkGraph = new PeopleNetworkGraph();
    try {
      File file = ResourceUtils.getFile("classpath:Relationship.txt");
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = reader.readLine();
      while (line != null) {
        String[] splitData = line.split(":");
        Integer id = Integer.parseInt(splitData[0].trim());
        List<String> connectionList = Arrays.asList(splitData[1].trim().split("\\s*,\\s*"));
        peopleNetworkGraph.addBidirectionalPaths(id, connectionList.stream().map(Integer::parseInt).collect(Collectors.toSet()));
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      LOGGER.error("IOException", e);
    }
    return peopleNetworkGraph;
  }
  
}
