# 1. Aşama: Build (Maven kullanarak jar oluşturma)
FROM maven:3.9.4-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# 2. Aşama: Run (Sadece gerekli olan JAR dosyasını çalıştırma)
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
