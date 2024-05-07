# TP TACS 2024-C1 - Compra Conjunta (Grupo 1)

El objetivo del TP es desarrollar una aplicación que sirva para realizar una
compra de un producto entre varias personas.
[Ver enunciado](https://docs.google.com/document/d/e/2PACX-1vRg7hKBnJ80MhyYrISjxbkf13QVZpInt-D6Fgg32tB_BTJwxDdVVlg3PjHW6Qzv-AlopUPsJDJoajPy/pub)

## Tecnologías

- Backend: Java 21, Spring Boot 3.2.4, Maven 3
- Frontend: Node 18, Next.js 14.2.3 w/React 18

## Cómo correr la aplicación en local

Cada proyecto (frontend y backend) cuenta con un archivo `README.md` en su
directorio raíz con las instrucciones necesarias para correr la aplicación en
local.

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

Una vez que la aplicación esté corriendo, se podrá corroborar que el backend
está funcionando correctamente accediendo a http://localhost:8080/health

Además, dicho healthcheck se encuentra configurado en el docker compose file
para esperar a que el backend esté listo antes de levantar el frontend, el cual
se puede acceder en http://localhost:3000/

### Dockerfile

Los archivos `Dockerfile` se encuentran en la raíz de ambos proyectos (frontend
y backend) y contienen las instrucciones necesarias para construir las imágenes
de docker de ambas aplicaciones.

En la primera capa del `Dockerfile` se utilizan las dependencias de desarrollo
para poder construir la aplicación.

Luego, en la segunda capa, se utilizan únicamente las dependencias de ejecución
además de los archivos generados en la capa anterior. Esta capa corre la
aplicación en modo no-root, para evitar problemas de seguridad.

## Cómo probar la aplicación backend

Dentro de la carpeta `docs` del proyecto se encuentra una colección y un entorno
de [Postman] que permiten probar los endpoints de la aplicación.

[Postman]: https://www.postman.com/

## Integrantes

| Apellido y Nombre | GitHub                                               |
|-------------------|------------------------------------------------------|
| Álvarez, Damián   | [@Damuchi99](https://github.com/Damuchi99)           |
| Galli, Patricio   | [@Patricio-Galli](https://github.com/Patricio-Galli) |
| Lingeri, Martín   | [@MartinLingeri](https://github.com/MartinLingeri)   |
| Pesce, Franco     | [@fpesce27](https://github.com/fpesce27)             |
| Ranieri, Agustín  | [@RaniAgus](https://github.com/RaniAgus)             |
