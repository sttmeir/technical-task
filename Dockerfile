# ---- Build stage ----
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# ---- Runtime stage ----
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy only the built jar from builder
COPY --from=builder /app/target/*.jar app.jar

# Optional: expose port and run profile
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]