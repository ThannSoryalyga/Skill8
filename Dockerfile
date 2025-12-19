# Use official OpenJDK 21 image as base
FROM openjdk:21-jdk-slim

# Set working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
# Make sure this matches your Maven build output
COPY target/mymemories-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8085 (Spring Boot app port)
EXPOSE 8085

# Command to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
