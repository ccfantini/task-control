
# Task Control API

It is a demonstrative REST API application for task management developed using Spring Boot with H2 database and OAuth2 to authentication.

## Settings
- It is not necessary to make any changes to the application.properties
- The data.sql file contains the data of users previously registered when starting the application.

## Getting Started

1. Install Java JDK 1.8 and set it as an environment variable.

2. Install Maven and set it as an environment variable.

3. Build the project with the following commands.

<code>mvn clean install</code>

<code>mvn package</code>

4. Execute the project.

<code>java -jar target/itau-task-control-0.0.1.jar</code>

5. Access the Swgger UI.

    http://localhost:8080/swagger-ui.htm


## How-to

1. Authenticate (OAuth2) the user using the example postman or curl command.

    <code>curl --location --request POST 'http://localhost:8080/oauth/token' --header 'Authorization: Basic aXRhdS10YXNrOml0YXUtdGFzay1zZWNyZXQ=' --form 'username=juliana' --form 'password=juli2020' --form 'grant_type=password'</code>

* It is also possible to use API through the swagger page.
* In the swagger, fill in the Authorization field the following "Bearer [ACCESS_TOKEN]"

2. Register task.

    <code>curl -X POST "http://localhost:8080/tasks" -H "accept: */*" -H "Authorization: Bearer l+2nilfBzQOudwMWJ6wuTRNWHKE=" -H "Content-Type: application/json" -d "{\"description\": \"teste 222\", \"status\": \"PENDING\", \"summary\": \"teste 222\"}"</code>

3. Search all tasks.

    <code>curl -X GET "http://localhost:8080/tasks" -H "accept: */*" -H "Authorization: Bearer l+2nilfBzQOudwMWJ6wuTRNWHKE="</code>

4. Update a task.

    <code>curl -X PUT "http://localhost:8080/tasks" -H "accept: */*" -H "Authorization: Bearer bnayOvEPJIrDDgGI8Xxizf6mUsw=" -H "Content-Type: application/json" -d "{ \"id\": 2, \"summary\": \"teste alterada\", \"description\": \"teste desc alterada\", \"status\": \"COMPLETED\" }"</code>

5. Search task by status.

    <code>curl -X GET "http://localhost:8080/tasks/status/PENDING" -H "accept: */*" -H "Authorization: Bearer +W7oZ3o7mZAFLlabMCb2mRyecx4="</code>

6. Delete task by ID.

    <code>curl -X DELETE "http://localhost:8080/tasks/1" -H "accept: */*" -H "Authorization: Bearer +W7oZ3o7mZAFLlabMCb2mRyecx4="</code>

## Health Check

   <code>curl -X GET "http://localhost:8080/actuator/health" -H "accept: */*" -H "Authorization: Bearer X/JptYEx1/y6gZGHYDbc2M6hDAA="</code>

## Log (HTTP Trace)

   <code>curl -X GET "http://localhost:8080/actuator/httptrace" -H "accept: */*" -H "Authorization: Bearer X/JptYEx1/y6gZGHYDbc2M6hDAA="</code>

## Metrics
    
* Search all available metrics
   
   <code>curl -X GET "http://localhost:8080/actuator/metrics" -H "accept: */*" -H "Authorization: Bearer X/JptYEx1/y6gZGHYDbc2M6hDAA="</code>
        
* Example of metrics to http requests
   
   <code>curl -X GET "http://localhost:8080/actuator/metrics/http.server.requests" -H "accept: */*" -H "Authorization: Bearer X/JptYEx1/y6gZGHYDbc2M6hDAA="</code>

## Technologies

   * [Spring Boot](https://spring.io/projects/spring-boot)
   * [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
   * [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html)
   * [H2 Database](https://www.h2database.com/html/main.html)
   * [SpringFox Swagger UI](https://springfox.github.io/springfox/docs/current/#springfox-swagger-ui)
   * [Spring Security OAuth2 Autoconfigure](https://docs.spring.io/spring-security-oauth2-boot/docs/2.0.0.RC2/reference/htmlsingle/)
   * [Maven](https://maven.apache.org/)
   * [Mockito](https://site.mockito.org/)
   * [Spring Boot Dev Tools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)



