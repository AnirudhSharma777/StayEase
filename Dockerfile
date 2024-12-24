# Use OpenJDK 23 as a base image
FROM openjdk:23-jdk-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradle /app/gradle
COPY gradlew /app/
COPY build.gradle /app/
COPY settings.gradle /app/

# Copy the rest of the application code
COPY src /app/src

# Run Gradle to build the project
RUN ./gradlew clean build --stacktrace -x test

# Use OpenJDK 23 runtime for the final image
FROM openjdk:23-jdk-slim

# Copy the jar file from the build stage
COPY --from=build /app/build/libs/demo-0.0.1-SNAPSHOT.jar stayease.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "stayease.jar"]
