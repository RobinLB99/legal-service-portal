# Dockerfile para ENTORNO DE DESARROLLO (Versión Robusta)

# Usar una imagen base que contenga Maven y JDK 21.
FROM maven:3.9.10-eclipse-temurin-21-alpine

# Establecer el directorio de trabajo.
WORKDIR /app

# Exponer los puertos necesarios.
EXPOSE 8080
EXPOSE 35729

# El comando por defecto se definirá en docker-compose, pero establecemos
# uno por defecto como buena práctica.
CMD ["./mvnw", "spring-boot:run"]