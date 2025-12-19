# --- Stage 1: Build the application using Maven + JDK 21 ---
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy Maven configuration and source code
COPY pom.xml .
COPY src ./src

# Build the Spring Boot JAR
RUN mvn clean package -DskipTests

# --- Stage 2: Run the application using Java 21 JRE ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8085

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]