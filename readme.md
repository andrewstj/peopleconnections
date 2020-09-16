For requirements see [requirements](requirements.md)

# Overview
This application uses Spring Boot to expose RESTful API's around loading of data for [People](src/main/resources/Person.txt) and their [Connections](src/main/resources/Relationship.txt). 

# Running
To run, just go to the top level directory and type:

`./gradlew bootRun`

(Must have Java 8 installed, on windows ./gradlew.bat instead)

After starting the application, you can see the swagger API's at:

http://localhost:8080/swagger-ui.html#/


# Testing

`./gradlew test`

Note, currently the only meaningful test is found at [PersonConnectionsRepositoryTest](src/test/java/com/tjandrews/kyoto/peopleconnections/business/PersonConnectionsRepositoryTest.java), which tests the various scenarios for pathsConnectingPeople.