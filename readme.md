# Descripción

Este proyecto es un ejemplo de aplicación web CRUD desarrollada para la gestión de un club de tenis, permitiendo administrar socios, reservas de pistas y eventos. Está construido utilizando **Jakarta Faces** para la interfaz de usuario, **Vue.js** para interactividad en el frontend, y **Java** para la lógica de negocio. El proyecto está estructurado y gestionado con **Maven**, asegurando una integración fluida de todas las dependencias y plugins necesarios.

## Tecnologías Utilizadas

- **Jakarta Faces**: Framework para construir interfaces de usuario basadas en componentes en Java.
- **Maven**: Herramienta de gestión de proyectos y automatización de construcción.
- **Vue.js**: Framework progresivo de JavaScript utilizado para construir interfaces de usuario interactivas.
- **Java**: Lenguaje de programación utilizado para la lógica de negocio y conexión con la base de datos.
- **HTML/CSS**: Lenguajes de marcado y estilo para la estructura y apariencia de la interfaz.
- **Hibernate**: Framework ORM para la gestión de bases de datos relacionales.
- **MySQL**: Sistema de gestión de bases de datos relacional utilizado para almacenar los datos de la aplicación.

## Estructura del Proyecto

- **`src/main/java`**: Contiene el código fuente en Java, incluyendo los controladores, servicios y modelos.
  - **`controllers`**: Maneja las solicitudes HTTP y las interacciones del usuario.
  - **`services`**: Contiene la lógica de negocio y la interacción con la capa de persistencia.
  - **`models`**: Define las entidades que representan las tablas de la base de datos.
- **`src/main/resources`**: Archivos de configuración, plantillas de interfaz y recursos estáticos.
  - **`templates`**: Contiene las vistas de la aplicación construidas con Jakarta Faces.
  - **`static`**: Archivos estáticos como CSS, JavaScript, e imágenes.
- **`pom.xml`**: Archivo de configuración de Maven que gestiona las dependencias del proyecto, como Hibernate, Jakarta Faces, y otros plugins necesarios para la compilación y despliegue.

## Funcionalidades Principales

- **Gestión de Socios**: Permite la creación, edición, visualización y eliminación de registros de socios del club.
- **Gestión de Reservas**: Los usuarios pueden realizar y gestionar reservas de pistas de tenis y otros recursos del club.
- **Gestión de Eventos**: Administración de eventos organizados por el club, incluyendo la programación y el registro de participantes.
- **Autenticación y Autorización**: Control de acceso basado en roles para proteger las funcionalidades sensibles de la aplicación.
- **Interfaz de Usuario**: Diseño intuitivo y responsive que facilita la interacción con la aplicación, construido con Jakarta Faces y enriquecido con Vue.js para una mejor experiencia de usuario.

## Instalación y Ejecución

1. Clona el repositorio: `git clone https://github.com/Vicen9/GestionClubTennis.git`
2. Navega al directorio del proyecto: `cd GestionClubTennis`
3. Configura la base de datos en el archivo `application.properties`.
4. Ejecuta la aplicación con Maven: `mvn clean install` y luego `mvn spring-boot:run`.
5. Accede a la aplicación en `http://localhost:8080`.

## Autor
* Vicente Castellano Gomez, vicentecastellano65@gmail.com - vcg00025@red.ujaen.es

