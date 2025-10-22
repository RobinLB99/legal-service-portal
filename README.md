# Legal Service Portal

> ⚠️ **Proyecto en Desarrollo** - Esta aplicación se encuentra actualmente en fase de desarrollo activo.

Plataforma web para la gestión de servicios legales e interacción entre abogados y clientes.

## 📋 Descripción

Legal Service Portal es una aplicación web desarrollada con Spring Boot que facilita la gestión de servicios legales, permitiendo la comunicación y colaboración entre abogados y sus clientes. La plataforma ofrece funcionalidades para administrar casos legales, perfiles de usuarios y autenticación segura.

## 🚀 Tecnologías

### Backend
- **Java 21**
- **Spring Boot 3.4.7**
- **Spring Security** - Autenticación y autorización
- **Spring Data JPA** - Persistencia de datos
- **PostgreSQL 17** - Base de datos
- **Lombok** - Reducción de código boilerplate

### Frontend
- **Thymeleaf** - Motor de plantillas
- **HTMX** - Interactividad dinámica
- **CSS/JavaScript** - UI/UX moderna

### DevOps
- **Docker & Docker Compose** - Contenerización
- **Maven** - Gestión de dependencias

## 📁 Estructura del Proyecto

```
legal-service-portal/
├── src/
│   ├── main/
│   │   ├── java/com/robinlb99/legalserviceportal/
│   │   │   ├── common/           # Utilidades comunes
│   │   │   ├── config/           # Configuraciones (Security, etc.)
│   │   │   ├── domain/           # Entidades del dominio
│   │   │   │   ├── client/       # Cliente
│   │   │   │   ├── lawyer/       # Abogado
│   │   │   │   ├── legalcase/    # Casos legales
│   │   │   │   ├── user/         # Usuarios
│   │   │   │   └── credential/   # Credenciales y permisos
│   │   │   └── features/         # Funcionalidades por módulo
│   │   │       ├── auth/         # Autenticación
│   │   │       ├── login/        # Login
│   │   │       ├── dashboard/    # Dashboard
│   │   │       └── myprofileaccountmanagement/ # Gestión de perfil
│   │   └── resources/
│   │       ├── static/           # Assets estáticos (CSS, JS, fonts)
│   │       └── templates/        # Plantillas Thymeleaf
│   └── test/                     # Tests
├── docker-compose.yaml           # Orquestación de contenedores
├── Dockerfile                    # Imagen Docker de desarrollo
├── pom.xml                       # Configuración Maven
└── .env.example                  # Variables de entorno de ejemplo
```

## 🛠️ Requisitos Previos

- **Docker** y **Docker Compose** instalados
- **Java 21** (si se ejecuta sin Docker)
- **Maven 3.9+** (si se ejecuta sin Docker)

## 🚦 Inicio Rápido

### Con Docker (Recomendado)

1. **Clonar el repositorio:**
   ```bash
   git clone <repository-url>
   cd legal-service-portal
   ```

2. **Configurar variables de entorno:**
   ```bash
   cp .env.example .env
   ```

3. **Iniciar los servicios:**
   ```bash
   docker-compose up
   ```

4. **Acceder a la aplicación:**
   - URL: http://localhost:8080
   - LiveReload: http://localhost:35729

### Sin Docker

1. **Configurar PostgreSQL:**
   - Crear base de datos `legalservice`
   - Usuario: `admin`
   - Contraseña: `admin`

2. **Compilar y ejecutar:**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

## 🔧 Configuración

### Variables de Entorno

Editar el archivo `.env` con las credenciales de PostgreSQL:

```env
POSTGRES_DB=legalservice
POSTGRES_USER=admin
POSTGRES_PASSWORD=admin
```

### Puertos

- **8080**: Aplicación principal
- **35729**: LiveReload (desarrollo)
- **5432**: PostgreSQL

## 🏗️ Arquitectura

El proyecto sigue una arquitectura modular basada en:

- **Domain-Driven Design (DDD)**: Entidades de dominio claramente definidas
- **Feature-Based Structure**: Organización por funcionalidades
- **Separation of Concerns**: Separación clara entre capas (Controller, Service, Repository)

### Módulos Principales

- **Auth**: Gestión de autenticación y seguridad
- **Dashboard**: Panel principal del usuario
- **Profile Management**: Gestión de perfiles de usuario
- **Legal Cases**: Administración de casos legales

## 🔐 Seguridad

- Autenticación basada en Spring Security
- Control de acceso basado en roles (RBAC)
- Gestión de permisos granular
- Passwords hasheados

## 🧪 Testing

```bash
./mvnw test
```

## 📦 Build

### Desarrollo
```bash
./mvnw spring-boot:run
```

### Producción
```bash
./mvnw clean package
java -jar target/legalserviceportal-1.0.0-SNAPSHOT.jar
```

## 🐳 Docker

### Build de imagen
```bash
docker build -t legal-service-portal .
```

### Ejecutar contenedor
```bash
docker-compose up -d
```

### Ver logs
```bash
docker-compose logs -f app
```

### Detener servicios
```bash
docker-compose down
```

## 📝 Licencia

Este proyecto está bajo la licencia especificada en el archivo `LICENCE`.

## 👥 Contribución

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📧 Contacto

Proyecto desarrollado por: robinlb99

---

**Nota**: Este proyecto está en desarrollo activo. Algunas funcionalidades pueden estar incompletas.
