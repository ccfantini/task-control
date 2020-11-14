
# Task Control API

It is a demonstrative REST API application for task management developed using Spring Bootcom H2 database and OAuth2 authentication protocol.

## Settings
- It is not necessary to make any changes to the application.properties
- The data.sql file contains the data of users previously registered when starting the application.

## Getting Started

1. Install Java JDK 1.8 and set it as an environment variable.
2. Install Maven and set it as an environment variable.
3. Build the project with the following command "mvn package".
4. Execute the project "java -jar target/itau-task-control-0.0.1.jar"
5. Access the resource using http://localhost:8080/swagger-ui.htm


## How-to

1. Authenticate (OAuth2) the user using the example postman or curl command.

    curl --location --request POST 'http://localhost:8080/oauth/token' --header 'Authorization: Basic aXRhdS10YXNrOml0YXUtdGFzay1zZWNyZXQ=' --form 'username=juliana' --form 'password=juli2020' --form 'grant_type=password'

* It is also possible to use API through the swagger page.
* In the swagger, fill in the Authorization field the following "Bearer [ACCESS_TOKEN]"

2. Register task.

    curl -X POST "http://localhost:8080/tasks" -H "accept: */*" -H "Authorization: Bearer l+2nilfBzQOudwMWJ6wuTRNWHKE=" -H "Content-Type: application/json" -d "{\"description\": \"teste 222\", \"status\": \"PENDING\", \"summary\": \"teste 222\"}"

3. Search all tasks.

    curl -X GET "http://localhost:8080/tasks" -H "accept: */*" -H "Authorization: Bearer l+2nilfBzQOudwMWJ6wuTRNWHKE="

4. Update a task.

    curl -X PUT "http://localhost:8080/tasks" -H "accept: */*" -H "Authorization: Bearer bnayOvEPJIrDDgGI8Xxizf6mUsw=" -H "Content-Type: application/json" -d "{ \"id\": 2, \"summary\": \"teste alterada\", \"description\": \"teste desc alterada\", \"status\": \"COMPLETED\" }"

5. Search task by status.

    curl -X GET "http://localhost:8080/tasks/status/{status}?status=COMPLETED" -H "accept: */*" -H "Authorization: Bearer bnayOvEPJIrDDgGI8Xxizf6mUsw="
