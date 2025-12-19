FROM maven:3.9.3-jdk-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (cache)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the Spring Boot JAR
RUN mvn clean package -DskipTests

# --- Stage 2: Run the application using JDK 21 ---
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8085
ENTRYPOINT ["java","-jar","app.jar"]
