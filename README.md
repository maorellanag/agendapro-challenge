# AgendaPro Desafío Java

Este proyecto ha sido desarrollado usando las siguientes tecnologías
- Java 17
- Maven 3.9.x
- Spring Boot 3.3.6
- GCP Platform
  - Cloud Build
  - Cloud Run

## Compilar Proyecto

Para compilar el proyecto ejecutando unit tests e integration tests

`mvn clean install`

Esto generará el archivo `target\agendapro-challenge-0.0.1-SNAPSHOT.jar`

## Dockerización

Para dockerizar el proyecto

`docker build -t agendapro-challenge .`

Esto generará una imagen docker con nombre agendapro-challenge

## Ejecutar Proyecto

Para crear un contenedor en base a la imagen docker previamente creada:

`docker run -p 8080:8080 agendapro-challenge`

De esta manera el puerto 8080 de la máquina local será redirigido al puerto 8080 del contenedor, permitiendo la comunicación desde un cliente HTTP.

## Despliegue

Este proyecto ha sido desplegado en la nube usando el provedor Google Cloud Platform.
Siendo accesible desde la siguiente URL:
- https://agendapro-challenge-app-13205890777.us-east1.run.app

Una documentación básica de los endpoints disponibles puede encontrarse en la siguiente ruta
- https://agendapro-challenge-app-13205890777.us-east1.run.app/swagger-ui/index.html

![img.png](resources/swagger.png)

Ha sido desplegado mediante un pipeline CI/CD utilizando las siguientes tecnologías:
- **Cloud Build:** Para construir el proyecto cuando se detecta un commit en la rama **main** de github.
- **Cloud Run:** Para levantar un contenedor en base a la imagen Docker creada previamente con Cloud Build.

## Recursos

Adjunto en el repositorio encontrará una carpeta de recursos que incluye lo siguiente:
- Postman collection con requests de ejemplo para las diferentes operaciones sobre el API REST.
- data.csv archivo con productos de ejemplo para ser cargados.

## Demo

[video demo](https://drive.google.com/file/d/1uJ82TuLZWaKM81Tpl1fbhprMKCIl-Cik/view?usp=sharing)