FROM maven:3.9.10-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copia el pom.xml y descarga las dependencias para cachearlas
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el resto del código fuente
COPY src ./src

# Expone el puerto de depuración
EXPOSE 5005

# Comando por defecto para ejecutar la aplicación en modo de desarrollo
CMD mvn spring-boot:run -Dspring-boot.run.profiles=dev
