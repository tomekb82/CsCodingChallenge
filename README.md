# CsCodingChallenge

## Description

Java program that parse itunes.json in order to produce the output as follows:

    Analysis: Total track time: <hours> hours, <mins> minutes, <secs> seconds
    Analysis: Number of different collections: <number of distinct collections>
    Analysis: Average price of a track <price >

## Compile project

Maven used for code compilation

    mvn clean install

### Java version

Java 11 used.

## Project structure

Project has been divided to separate packages containing classes responsible for different functionalities(Single Responsibility).
Common functionality has been extracted to external classes and functions (DRY). Progamming interfaces has been used, classes are opened for extension, closed for modifications.
However, for some parts Java 8 stream and functional programming has been used to provide more flexibility during further project development/extension.

### main

The 'com.itunes contains main class CsCodingChallenge implementing logic mentioned in description.

### service

Package contains services implementing specific functionality such as: calculations, json file processing.

### model

Package contains model classes, enumerations etc.

### util

Package contains some utility classes.

## Run project

### production mode (default):

Two parameters required.

    java -jar target\CsCodingChallenge-1.0.jar itunes.json

### development mode (with debug):

Two paramters required. Debug flag can be set to print all required data from parsed file.

    java -Ddebug=true -jar target\CsCodingChallenge-1.0.jar itunes2.json

### Tests

#### Junit test classes 

Unit tests includes: calculations checks, json files processing tests, main processing functinos.
Test has been prepared during development phase according to DDD metodology.

Following test classes used: 
    CsCodingChallengeTest, 
    CalculationsTest, 
    JsonProcessingTest

#### Running tests

    mvn test
    
### Dependencies

Library dependencies: junit, json-simple

### Maven plugins

Following maven plugins used: maven-jar-plugin, maven-shade-plugin, maven-compiler-plugin
