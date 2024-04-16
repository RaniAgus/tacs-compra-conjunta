# TP TACS 2024-C1 - Grupo 1

El objetivo del TP es desarrollar una aplicación que sirva para realizar una
compra de un producto entre varias personas.
[Ver enunciado](https://docs.google.com/document/d/e/2PACX-1vRg7hKBnJ80MhyYrISjxbkf13QVZpInt-D6Fgg32tB_BTJwxDdVVlg3PjHW6Qzv-AlopUPsJDJoajPy/pub)

## Tecnologías

- Backend: Java 21, Spring Boot 3.2.4, Maven 3

## Cómo correr la aplicación con Docker

El proyecto cuenta con un archivo `compose.yml` que permite correr la
aplicación en un contenedor de Docker.

Para correr la aplicación, primero hay que configurar las variables de entorno
creando un archivo `.env` en la raíz del proyecto basándose en el archivo
`env.example`.

Luego, simplemente debemos ejecutar el siguiente comando en el directorio raíz
del proyecto:

```bash
docker compose up
```

Una vez que la aplicación esté corriendo, se podrá corroborar que está
funcionando correctamente accediendo a la siguiente URL:

```
http://localhost:8080/healthcheck
```

### Dockerfile

El archivo `Dockerfile` se encuentra en la raíz del proyecto y contiene las
instrucciones necesarias para construir la imagen de Docker de la aplicación.

En la primera capa del `Dockerfile` se utiliza la versión JDK para construir la
aplicación utilizando el maven wrapper que se encuentra en el repositorio.

Luego, en la segunda capa, se utiliza la versión JRE para ejecutar la aplicación
conteniendo únicamente el archivo JAR generado en la capa anterior.
Esta capa corre la aplicación en modo no-root, para evitar problemas de
seguridad.

## Cómo probar la aplicación

Dentro de la carpeta `docs` del proyecto se encuentra una colección y un entorno
de Postman que permiten probar los endpoints de la aplicación.

## Integrantes

| Apellido y Nombre | GitHub                                               |
|-------------------|------------------------------------------------------|
| Álvarez, Damián   | [@Damuchi99](https://github.com/Damuchi99)           |
| Galli, Patricio   | [@Patricio-Galli](https://github.com/Patricio-Galli) |
| Lingeri, Martín   | [@MartinLingeri](https://github.com/MartinLingeri)   |
| Pesce, Franco     | [@fpesce27](https://github.com/fpesce27)             |
| Ranieri, Agustín  | [@RaniAgus](https://github.com/RaniAgus)             |
