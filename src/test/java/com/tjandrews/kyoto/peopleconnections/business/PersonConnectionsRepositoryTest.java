package com.tjandrews.kyoto.peopleconnections.business;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import com.tjandrews.kyoto.peopleconnections.business.models.PersonConnectionPath;
import com.tjandrews.kyoto.peopleconnections.infrastructure.PeopleNetworkGraphDao;
import com.tjandrews.kyoto.peopleconnections.infrastructure.models.PeopleNetworkGraph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PersonConnectionsRepositoryTest {
  @MockBean
  private PeopleNetworkGraphDao personConnectionsDao;

  @Autowired
  private PersonConnectionsRepository personConnectionsRepository;

  @BeforeEach
  public void before() {
    PeopleNetworkGraph peopleNetworkGraph = new PeopleNetworkGraph();
    peopleNetworkGraph.addBidirectionalPaths(1, Arrays.asList(2,3,4));
    peopleNetworkGraph.addBidirectionalPaths(2, Arrays.asList(3,6));
    peopleNetworkGraph.addBidirectionalPaths(3, Arrays.asList(4,5));
    peopleNetworkGraph.addBidirectionalPaths(4, Arrays.asList(5));
    peopleNetworkGraph.addBidirectionalPaths(5, Arrays.asList(6));
    peopleNetworkGraph.addBidirectionalPaths(6, Arrays.asList(7));
    given(personConnectionsDao.getPeopleNetworkGraph()).willReturn(peopleNetworkGraph);
    personConnectionsRepository.initializeData();
  }

  @Test
  public void getPathsConnectingPeople_depthOne_returnsOnePath() {
    // arrange
    Collection<PersonConnectionPath> expected = new ArrayList<PersonConnectionPath>();
    expected.add(new PersonConnectionPath(Arrays.asList(1, 2, 6)));

    // act
    Collection<PersonConnectionPath> actual = personConnectionsRepository.getPathsConnectingPeople(1, 6, Optional.of(1)).get();

    // assert
    assertTrue(Arrays.deepEquals(actual.toArray(), expected.toArray()));
  }

  @Test
  public void getPathsConnectingPeople_depthTwo_returnsFourPaths() {
    // arrange
    Collection<PersonConnectionPath> expected = new ArrayList<PersonConnectionPath>();
    expected.add(new PersonConnectionPath(Arrays.asList(1, 2, 6)));
    expected.add(new PersonConnectionPath(Arrays.asList(1, 3, 2, 6)));
    expected.add(new PersonConnectionPath(Arrays.asList(1, 3, 5, 6)));
    expected.add(new PersonConnectionPath(Arrays.asList(1, 4, 5, 6)));

    // act
    Collection<PersonConnectionPath> actual = personConnectionsRepository.getPathsConnectingPeople(1, 6, Optional.of(2)).get();

    // assert
    assertTrue(Arrays.deepEquals(actual.toArray(), expected.toArray()));
  }

  @Test
  public void getPathsConnectingPeople_depthThree_returnsEightPaths() {
    // arrange
    Collection<PersonConnectionPath> expected = new ArrayList<PersonConnectionPath>();
    expected.add(new PersonConnectionPath(Arrays.asList(1, 2, 3, 5, 6)));
    expected.add(new PersonConnectionPath(Arrays.asList(1, 2, 6)));
    expected.add(new PersonConnectionPath(Arrays.asList(1, 3, 2, 6)));
    expected.add(new PersonConnectionPath(Arrays.asList(1, 3, 4, 5, 6)));
    expected.add(new PersonConnectionPath(Arrays.asList(1, 3, 5, 6)));
    expected.add(new PersonConnectionPath(Arrays.asList(1, 4, 3, 2, 6)));
    expected.add(new PersonConnectionPath(Arrays.asList(1, 4, 3, 5, 6)));
    expected.add(new PersonConnectionPath(Arrays.asList(1, 4, 5, 6)));

    // act
    Collection<PersonConnectionPath> actual = personConnectionsRepository.getPathsConnectingPeople(1, 6, Optional.of(3)).get();

    // assert
    assertTrue(Arrays.deepEquals(actual.toArray(), expected.toArray()));
  }

  @Test
  public void getPathsConnectingPeople_depthTwoReverse_returnsFourPaths() {
    // arrange
    Collection<PersonConnectionPath> expected = new ArrayList<PersonConnectionPath>();
    expected.add(new PersonConnectionPath(Arrays.asList(6, 2, 1)));
    expected.add(new PersonConnectionPath(Arrays.asList(6, 2, 3, 1)));
    expected.add(new PersonConnectionPath(Arrays.asList(6, 5, 3, 1)));
    expected.add(new PersonConnectionPath(Arrays.asList(6, 5, 4, 1)));
    
    // act
    Collection<PersonConnectionPath> actual = personConnectionsRepository.getPathsConnectingPeople(6, 1, Optional.of(2)).get();

    // assert
    assertTrue(Arrays.deepEquals(actual.toArray(), expected.toArray()));
  }
  
}
