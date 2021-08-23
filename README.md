# ZOOPLUS API TESTING

## Pre Requisite:
1. Install maven following [these](https://docs.wso2.com/display/IS323/Installing+Apache+Maven+on+Windows) guidelines.

2. Install java 8 from [this](https://www.java.com/en/download/) link.



## Build
To build your application tests simply run: `mvn clean install` and it will execute all tests and if they execute successfully then it will create build.

## Starting Application
To start your application, simply run: 

    java -jar [name_of_jar]

To check the Test Report, view the HTML report in target folder with name index.html. 

### Code quality
Sonarlint is used to analyse code quality and remove code smells.

