
FROM eclipse-temurin:18-jre

WORKDIR /app

COPY build/libs/Back-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
