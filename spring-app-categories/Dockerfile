FROM eclipse-temurin:23-jre

ENV VERSION="1.0.0-SNAPSHOT"

ENV SERVER_PORT=8080

ENV EUREKA_INSTANCE_INSTANCE_ID=1

ENV SPRING_CONFIG_IMPORT=optional:configserver:http://localhost:8085

EXPOSE 8080

COPY target/spring-app-categories-${VERSION}.jar /opt/spring-app-categories/spring-app-categories.jar

CMD ["java", "-jar", "/opt/spring-app-categories/spring-app-categories.jar"]

