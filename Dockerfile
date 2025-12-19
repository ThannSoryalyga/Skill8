# Stage 1: Build
FROM eclipse-temurin:21-jdk-focal AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (cache optimization)
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre-focal

WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/mymemories-0.0.1-SNAPSHOT.jar app.jar

# Expose port (if your Spring Boot app runs on 8080)
EXPOSE 8085

# Start the app
ENTRYPOINT ["java","-jar","app.jar"]
