# binarydiff

This is a sample Java / Maven / Spring Boot (version 2.0.4) application.

Example of how to use Spring Boot with RESTful web services to receive, compare and save JSON base64 encoded binary data.

Project Objects: demonstrate how to use SpringBoot MicroSevice with RESTful web services to receive JSON base64 encoded binary data, compare the received files, response the difference and save the files for future verification.
For the purpose of this project I´m using Base64 encoding for the binary data. Base64 encoding for the binary data, it is transformed into text   and then can be sent with an application/json content type as another property of the JSON payload. It is not at all efficient especially if you are dealing with low bandwidth and intermittent network, because the encoded binary data will increase the payload size.

## How to Run
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary.

- Clone this repository
- Make sure you are using JDK 1.8 and Maven 3.x
- You can build the project and run the tests by running mvn clean package

```sh
        java -jar -Dspring.profiles.active=test target/spring-boot-rest-example-0.5.0.war
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```

## About the Service

- This project is using Swagger documentation: 

<host>/swagger-ui.html#/file45diff45controller
or
<host>/v2/api-docs

### EndPoints
Provide 2 http endpoints that accepts JSON base64 encoded binary data on both
endpoints:
- POST <host>/filediff/api/v1/diff/{id}/left
- POST <host>/filediff/api/v1/diff/{id}/right


```sh
Accept: application/json
Content-Type: application/json
{
 "name": { type: "string" },
 "data": { type: "string" }
}
```
  

Result EndPoint - provide the following info in JSON format
GET <host>/filediff/api/v1/diff/{id}:
 
```sh
Accept: application/json
Content-Type: application/json
{
 "fileId": { type: "string" },
 "filesAreEqual": { type: "string" },
 "sameSize": { type: "boolean" },
 "sizeFileLeft": { type: "integer" },
 "sizeFileRight": { type: "integer" },
}
```
  
