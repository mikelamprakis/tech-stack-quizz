# Use Eclipse Temurin (OpenJDK) base image
FROM eclipse-temurin:21-jdk AS build

# Copy project files
WORKDIR /app
COPY . .

# Install Maven
RUN apt-get update && apt-get install -y maven && rm -rf /var/lib/apt/lists/*

# Build the JAR with Maven/Gradle
RUN mvn clean package -DskipTests

# ======================
# Run stag
# ======================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the fat JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Copy the required folders to the expected location
COPY --from=build /app/src/main/resources/questions /app/src/main/resources/questions
COPY --from=build /app/src/main/resources/static /app/src/main/resources/static


# Expose port 8080
EXPOSE 8085

# Run Spring Boot
ENTRYPOINT ["java","-jar","/app/app.jar"]
