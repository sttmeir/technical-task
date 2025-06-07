FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Копируем jar-файл, собранный заранее
COPY target/car-service.jar app.jar

# Активируем docker-профиль
ENTRYPOINT ["java", "-jar", "app.jar"]