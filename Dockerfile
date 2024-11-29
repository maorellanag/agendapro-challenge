FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY target/*.jar agendapro-challenge.jar
ENTRYPOINT ["java", "-jar", "/app/agendapro-challenge.jar"]