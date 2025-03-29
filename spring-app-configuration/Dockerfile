FROM eclipse-temurin:23-jre

ENV VERSION="1.0.0-SNAPSHOT"

ENV SERVER_PORT=8080

ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8084/eureka
ENV EUREKA_INSTANCE_INSTANCE_ID=1

ENV SPRING_PROFILES_ACTIVE=native

ENV SPRING_CLOUD_CONFIG_SERVER_NATIVE_SEARCH_LOCATIONS=file:/var/spring-app-configuration/configuration

VOLUME /var/spring-app-configuration/configuration

EXPOSE 8080

COPY target/spring-app-configuration-${VERSION}.jar /opt/spring-app-configuration/spring-app-configuration.jar

CMD ["java", "-jar", "/opt/spring-app-configuration/spring-app-configuration.jar"]



