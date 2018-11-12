# CsCodingChallenge

## Description

Java program that parse itunes.json in order to produce the output as follows:

Analysis: Total track time: <hours> hours, <mins> minutes, <secs> seconds
Analysis: Number of different collections: <number of distinct collections>
Analysis: Average price of a track <price >

## Compile project

    mvn clean install

### Java version

Java 11 used.

## Run project

### production mode (default):

    java -jar target\CsCodingChallenge-1.0.jar itunes.json

### development mode (with debug):

    java -jar target\CsCodingChallenge-1.0.jar itunes2.json

### Tests

#### Running tests

    mvn test

#### Junit test classes

Following test classes used: CsCodingChallengeTest, CalculationsTest, JsonProcessingTest

### Dependencies

Library dependencies: junit, json-simple

### Maven plugins

Used following maven plugins: maven-jar-plugin, maven-shade-plugin, maven-compiler-plugin