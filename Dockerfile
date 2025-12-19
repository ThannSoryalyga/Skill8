# Use official OpenJDK 17 image (stable LTS)
FROM openjdk:17-jdk-slim

# Set working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/mymemories-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8085
EXPOSE 8085

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
