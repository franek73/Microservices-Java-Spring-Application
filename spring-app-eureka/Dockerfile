FROM eclipse-temurin:23-jre

ENV VERSION="1.0.0-SNAPSHOT"

ENV SERVER_PORT=8080

ENV EUREKA_INSTANCE_HOSTNAME=localhost

ENV EUREKA_CLIENT_FETCH_REGISTRY=false
ENV EUREKA_CLIENT_REGISTER_WITH_EUREKA=false
ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://localhost:8080/eureka

ENV EUREKA_SERVER_ENABLE_SELF_PRESERVATION=false
ENV EUREKA_SERVER_EVICTION_INTERVAL_TIMER_IN_MS=2000

EXPOSE 8080

COPY target/spring-app-eureka-${VERSION}.jar /opt/spring-app-eureka/spring-app-eureka.jar

CMD ["java", "-jar", "/opt/spring-app-eureka/spring-app-eureka.jar"]


