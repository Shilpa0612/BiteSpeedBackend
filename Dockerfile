# Use the official Gradle image as a base image
FROM gradle:jdk18 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the content of the local src directory to the working directory
COPY . .

COPY build.gradle .
# Build the application using Gradle
RUN gradle build --no-daemon

RUN ls -l /app/build/libs

# Use the official Java runtime base image
#FROM openjdk:11-jre-slim
FROM eclipse-temurin:18-jre

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file from the build stage
#COPY --from=build /app/build/libs/*.jar app.jar
COPY --from=build /app/build/libs/Back-1.0-SNAPSHOT.jar app.jar

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar"]
