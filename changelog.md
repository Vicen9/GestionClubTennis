# Changelog
Todos los cambios notables en este proyecto serán documentados en este archivo

## Iteración 1 

### Added

- Creación de jsp, por Vicente Castellano Gómez
- Creación templates, por Jose Manuel Gamarra Espinar 
- Implementación de web.xml, por Vicente Castellano Gómez
- Implementación vistas, por Vicente Castellano Gómez
- Implementación de patrones DAO, por Jose Manuel Gamarra Espinar
- Implementación controlador, por Jose Manuel Gamarra Espinar
- Creación de modelo de vistas, por Vicente Castellano Gómez
- Recuperación de parametros en URLS, por Jose Manuel Gamarra Espinar
- Envio de información con formularios, por Vicente y Jose

### Changed

- HTML y CSS asignados a la carpeta WEB-INF, por Jose Manuel Gamarra Espinar
- Eliminacion jsp como pedía la práctica por Vicente Castellano Gómez

### Fixed

- Actualización del readme 
- Actualización del changelog

### Tag
https://gitlab.ujaen.es/DAW/2223/proyecto208/GestionaTennis/tags/V1.0

## Iteración 2
### Added
- Creacion de vistas utilizando Facelets por Vicente Castellano Gomez
- Instanciación y persistencia de objetos en JEE por Jose Manuel Gamarra Espinar
- Controlador y modelos de datos por Jose Manuel Gamarra Espinar
- Gestion del layout y envio de informacion por formularios por Vicente Castellano Gomez
- Ciclo de ejecucion JSF y validacion de informacion por formularios por Jose Manuel Gamarra Espinar
- Componentes actualizados y asincronizacion por Jose Manuel Gamarra Espinar
- Uso de Primefaces por Vicente Castellano Gomez
- Validacion de Beans por Jose y por Vicente


### Changed
- Modelos de datos y recuperacion de parametros por Vicente Castellano Gomez

### Fixed
- Arreglados los problemas de la iteracion 1 de formularios etc por Vicente Castellano Gomez

### Tag
https://gitlab.ujaen.es/DAW/2223/proyecto208/GestionaTennis/tags/V2.0

## Iteración 3

### Added
- Vista y componente de Torneo por Vicente Castellano Gómez
- DAO de pista por Vicente Castellano Gómez y Jose Manuel Gamarra Espinar

- Implementación de DAO basado en JPA de Socio por Vicente Castellano Gómez
- Implementación de DAO basado en JPA y DAO de Torneo por Jose Manuel Gamarra Espinar
- Añadido archivo de precarga de datos por Vicente Castellano Gómez y Jose Manuel Gamarra Espinar
- Implementación de la clase Preferencia por Jose Manuel Gamarra Espinar
- Añadido de control de acceso a recursos por Vicente Castellano Gómez
- Añadido de formulario de auntenticación por Jose Manuel Gamarra Espinar
- Añadido de LogOut en menú por Vicente Castellano Gómez
- Añadido de identidad personalizado por Jose Manuel Gamarra Espinar
### Changed
- BEAN de Torneo, añadido atributo descripción por Vicente Castellano Gómez
### Fixed
- Error en DAO de torneo, en el método recupera(String name)
- Arreglado anotación de dni, para indicar el patron deseado
### Tag
https://gitlab.ujaen.es/DAW/2223/proyecto208/GestionaTennis/tags/Iteracion3

## Iteración 4

### Added
- Creación de IdentityStore por Jose Manuel Gamarra Espinar y Vicente Castellano Gómez
- Creación de loginController por Jose Manuel Gamarra Espinar
- Creacion de vistas de reservar e inscribir por Vicente Catellano Gómez
- Creación de Crea Pistas y Crea Torneo por Vicente Castellano Gómez y Jose Manuel Gamarra Espinar
- 
### Changed
- Login con el correo
- Metodos getPass y getRol eliminados
### Fixed
- Reparación de editaSocio, bug no permitia recuperar la contraseña y el sexo
### Tag

## Iteración 5

### Added
- Implementación de inscripción de Torneo con Vue.js con conexiones asíncronas para intercambio de datos y modificación dinamico del DOM. por Vicente Castellano Gómez y Jose Manuel Gamarra Espinar
- Implementación de APIREST de Socio, Torneo y Pista por Vicente Castellano Gómez
- Implementación de Filtro CORSAP por Jose Manuel Gamarra Espinar
- Implementación del BEAM Inscribe, junto a sus DAO correspondientes por Vicente Castellano y Jose Manuel Gamarra
- Implementación de APIREST de inscribe y reserva por Vicente Castellano y Jose Manuel Gamarra
- Añadido lista de torneos inscritos y pistas reservadas en el footer por Vicente Castellano
- Validación en la parte del cliente ValidaTorneo y ValidaPista por Jose Manuel Gamarra
- Validación en la parte del cliente ValidaSocio por Vicente Castellano
### Changed
- Adaptación de torneo a VUE
### Fixed
- Bug en el que no se veía la lista de pistas e inscripciones si eras administrador
### Tag
https://gitlab.ujaen.es/DAW/2223/proyecto208/GestionaTennis/tags/Iteracion5