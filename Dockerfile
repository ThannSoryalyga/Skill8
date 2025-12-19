# Use Maven + JDK to build the app
FROM maven:3.9.1-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml and download dependencies first (caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Use lightweight JDK image to run the app
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/mymemories-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "app.jar"]
