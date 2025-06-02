# Aplicación Bookstore

¡Bienvenido/a a la **Aplicación Bookstore**! Este es un proyecto basado en Spring Boot para gestionar una librería, utilizando MySQL como base de datos. La aplicación está configurada para ejecutarse tanto localmente como en contenedores Docker.

---

## Tabla de Contenidos

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Prerrequisitos](#prerrequisitos)
- [Cómo Empezar](#cómo-empezar)
  - [Clonar el Repositorio](#clonar-el-repositorio)
  - [Estructura del Proyecto](#estructura-del-proyecto)
  - [Instrucciones de Configuración](#instrucciones-de-configuración)
    - [Ejecutar Localmente sin Docker](#ejecutar-localmente-sin-docker)
    - [Ejecutar con Docker](#ejecutar-con-docker)
- [Configuración](#configuración)
  - [Propiedades de la Aplicación](#propiedades-de-la-aplicación)
  - [Configuración de Docker](#configuración-de-docker)
- [Compilar la Aplicación](#compilar-la-aplicación)
- [Ejecutar la Aplicación](#ejecutar-la-aplicación)
- [Acceder a la Aplicación](#acceder-a-la-aplicación)
- [Solución de Problemas](#solución-de-problemas)
- [Contribuir](#contribuir)
- [Licencia](#licencia)

---

## Descripción del Proyecto

La **Aplicación Bookstore** es una aplicación web RESTful construida con Spring Boot que permite gestionar libros en una librería. Utiliza MySQL como base de datos y está configurada para ejecutarse en contenedores Docker, facilitando su despliegue y escalabilidad.

---

## Tecnologías Utilizadas

- **Java**: 17
- **Spring Boot**: 3.5.0
- **Spring Data JPA**: Para operaciones con la base de datos
- **MySQL**: 8.0.33 (Base de datos)
- **Docker**: Para contenedorización
- **Maven**: Gestión de dependencias y compilación
- **Lombok**: Para reducir código repetitivo
- **H2 Database**: Para pruebas (opcional)
- **Spring Boot Validation**: Para validación de entradas

---

## Prerrequisitos

Para ejecutar la aplicación localmente, asegúrate de tener instalado lo siguiente:

- **Java 17** o superior
- **Maven** (para compilar el proyecto)
- **Docker** y **Docker Compose** (para la configuración con contenedores)
- **Git** (para clonar el repositorio)
- **MySQL** (opcional, para ejecución local sin Docker)
- Un editor de código como IntelliJ IDEA, VS Code o Eclipse

---

## Cómo Empezar

### Clonar el Repositorio

Clona el repositorio en tu máquina local:

```bash
git clone https://github.com/ValentinoL15/bookstore.git
cd bookstore
```

### Estructura del Proyecto

```plaintext
bookstore/
├── src/                          # Código fuente de la aplicación Spring Boot
├── target/                       # Archivos compilados (JAR)
├── Dockerfile                    # Configuración de Docker para la aplicación
├── docker-compose.yml            # Configuración de Docker Compose
├── application.properties        # Configuración para entorno local
├── application-docker.properties # Configuración para entorno Docker
├── pom.xml                       # Configuración de dependencias y compilación de Maven
└── README.md                     # Este archivo
```

### Instrucciones de Configuración

Puedes ejecutar la aplicación de dos formas: localmente sin Docker o usando Docker.

#### Ejecutar Localmente sin Docker

1. **Instalar MySQL Localmente**:
   - Asegúrate de tener MySQL 8.0.33 o compatible instalado.
   - Crea una base de datos llamada `book_store`:
     ```sql
     CREATE DATABASE book_store;
     ```

2. **Configurar Variables de Entorno**:
   - El archivo `application.properties` usa variables de entorno para la conexión a la base de datos. Asegúrate de tener configurado lo siguiente:
     ```plaintext
      DB_URL_LOCAL=jdbc:mysql://localhost:3306/book_store?createDatabaseIfNotExist=true&serverTimezone=UTC
      DB_USER=tu_usuario_mysql
      DB_PASSWORD=tu_contraseña_mysql
     ```
   - Puedes establecer estas variables en tu IDE, en el sistema, o modificar directamente el archivo `application.properties`.

3. **Compilar la Aplicación**:
   ```bash
   mvn clean package
   ```

4. **Ejecutar la Aplicación**:
   ```bash
   java -jar target/bookstore-0.0.1-SNAPSHOT.jar
   ```

#### Ejecutar con Docker

1. **Asegurarte de tener Docker y Docker Compose Instalados**:
   - Verifica que Docker esté funcionando:
     ```bash
     docker --version
     docker-compose --version
     ```

2. **Compilar y Ejecutar con Docker Compose**:
   - Desde la raíz del proyecto, ejecuta:
     ```bash
     docker-compose up --build
     ```
   - Esto hará lo siguiente:
     - Construirá la imagen de la aplicación Spring Boot.
     - Iniciará un contenedor MySQL (`bookstore-db`) con la base de datos `book_store`.
     - Iniciará el contenedor de la aplicación (`bookstore`) y lo conectará con el contenedor MySQL.

3. **Detener los Contenedores**:
   - Para detener los contenedores, usa:
     ```bash
     docker-compose down
     ```

---

## Configuración

### Propiedades de la Aplicación

La aplicación utiliza dos archivos de propiedades:

- **`application.properties`** (para ejecución local):
  ```properties
  spring.application.name=bookstore
  spring.jpa.hibernate.ddl-auto=update
  spring.datasource.url=${DB_URL_LOCAL}
  spring.datasource.username=${DB_USER}
  spring.datasource.password=${DB_PASSWORD}
  spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
  ```

- **`application-docker.properties`** (para ejecución con Docker):
  ```properties
  spring.datasource.url=${DB_URL}
  spring.datasource.username=${DB_USER}
  spring.datasource.password=${DB_PASSWORD}
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
  ```

### Configuración de Docker

- **Dockerfile**:
  ```dockerfile
  FROM openjdk:17-jdk-slim

  ARG JAR_FILE=target/bookstore-0.0.1-SNAPSHOT.jar
  COPY ${JAR_FILE} app_bookstore.jar
