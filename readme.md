For requirements see [requirements](requirements.md)

# Overview
This application uses Spring Boot to expose RESTful API's around loading of data for [People](src/main/resources/Person.txt) and their [Connections](src/main/resources/Relationship.txt). 


# Running
To run, just go to the top level directory and type:

./gradlew bootRun

(Must have Java 8 installed)

After starting the application, you can see the swagger API's at:

http://localhost:8080/swagger-ui.html#/