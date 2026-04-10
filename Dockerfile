# Etapa 1: Construcción (Build) usando Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Copiamos los archivos de tu proyecto
COPY pom.xml .
COPY src ./src
# Construimos el archivo .jar ignorando los tests
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run) usando Java 21
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copiamos el .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto
EXPOSE 9000
# Comando para arrancar tu servidor
ENTRYPOINT ["java", "-jar", "app.jar"]