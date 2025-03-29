FROM eclipse-temurin:23-jre

ENV VERSION="1.0.0-SNAPSHOT"

ENV SERVER_PORT=8080

ENV GATEWAY_HOST=localhost:8080

ENV EUREKA_INSTANCE_INSTANCE_ID=1

ENV SPRING_CONFIG_IMPORT=optional:configserver:http://localhost:8085

EXPOSE 8080

COPY target/spring-app-gateway-${VERSION}.jar /opt/spring-app-gateway/spring-app-gateway.jar

CMD ["java", "-jar", "/opt/spring-app-gateway/spring-app-gateway.jar"]
