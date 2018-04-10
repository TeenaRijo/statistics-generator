# statistics-generator



This repository contains a solution to the problem listed below:

We would like to have a restful API for our statistics. The main use case for our API is to
calculate realtime statistic from the last 60 seconds. There will be two APIs, one of them is
called every time a transaction is made. It is also the sole input of this rest

## Logic of the API

POST /transactions

*The Main Logic  used is whenever a transaction comes in it check whether it is within last 60 seconds if so add it to transactionMap*

* Whenever a /statistics is called it calculates the statistics of last sixty seconds * 
If there are transactions which are not within the last 60s, they are cleared from the map before calculating statistics

## Building the Project and Deploying the application

C:/folder_name>mvn clean install
Once the build is success, move inside the target folder
C:/folder_name> cd target
C:/folder_name/target> java  -jar statistics-generator-0.0.1-SNAPSHOT.jar

[To edit the server port change the port number in \statistics-generator\src\main\resourcesapplication.properties]

##Sample Request

POST: http://localhost:8080/api/transactions

Request Body
{
	"amount":105.0,
	"timestamp":1534577890
	
}

http://localhost:8080/api/statistics

Response Body
{
    "sum": 0,
    "avg": 0,
    "max": 0,
    "min": 0,
    "count": 0
}


Tech stack used

Developed in Java
Used spring boot for developing the application
Maven as a build tool
// Need to implement Log4j as logging framework
Test cases in Junit
