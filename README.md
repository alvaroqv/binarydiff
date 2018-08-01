# binarydiff

This is a sample Java / Maven / Spring Boot (version 2.0.4) application.

Example of how to use Spring Boot with RESTful web services to receive, compare and save JSON base64 encoded binary data.

Project Goals: demonstrate how to use SpringBoot MicroSevice with RESTful web services to receive JSON base64 encoded binary data, compare the received files, response the difference and save the files for future verification.
For the purpose of this project I´m using Base64 encoding for the binary data. Base64 encoding for the binary data, it is transformed into text   and then can be sent with an application/json content type as another property of the JSON payload. It is not at all efficient especially if you are dealing with low bandwidth and intermittent network, because the encoded binary data will increase the payload size.

## How to Run

- Clone this repository
- Make sure you are using JDK 1.8 and Maven 3.x
- You can build the project and run the tests by running mvn clean package

## About the Service
- Default host: http://localhost:8080

- This project is using Swagger documentation: 

```sh
<host>/swagger-ui.html   
or
<host>/v2/api-docs
```

Example: http://localhost:8080/swagger-ui.html

### EndPoints
Provide 2 http endpoints that accepts JSON base64 encoded binary data on both
endpoints:
- POST <host>/filediff/api/v1/diff/{id}/left
- POST <host>/filediff/api/v1/diff/{id}/right


```sh
Accept: application/json
Content-Type: application/json
{
  "data": "string",
  "name": "string"
}
```
  

Result EndPoint - provide the following info in JSON format
GET <host>/filediff/api/v1/diff/{id}:
 
```sh
Accept: application/json
Content-Type: application/json
{
  "fileId": "string",
  "filesAreEqual": true,
  "sameSize": true,
  "sizeFileLeft": 0,
  "sizeFileRight": 0
}
```
The result shows if the files are equals or differences and it shows the files size.
