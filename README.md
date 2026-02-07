# Instruments website

This repository contains a Java Spring microservices web application for managing musical instruments, developed as part of the **Architektura Usług Internetowych** course. The application is containerized with Docker for easier deployment.

## Requirements

The following tools are required to build and run the project:

- OpenJDK 17
- Apache Maven 3.8
- Node.js 18
- npm 9.5
- Angular CLI 16

## Configuration

Application configuration can be updated in `application.properties` or via environment variables.

## Building

To build the project, run:

```bash
mvn clean package
```

To run the application using the embedded Apache Tomcat server, execute:

```bash
java -jar target/simple-rpg-1.0.0-SNAPSHOT.jar
```
