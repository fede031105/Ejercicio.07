# Etapa 1: Construcción (Build) usando Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run) usando Java 21
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Forzamos a Java a arrancar usando exactamente el puerto que Render exige
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=${PORT:-9000}"]