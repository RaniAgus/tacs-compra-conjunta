# TP TACS 2024-C1 - Compra Conjunta (Grupo 1)

El objetivo del TP es desarrollar una aplicación que sirva para realizar una
compra de un producto entre varias personas.
[Ver enunciado](https://docs.google.com/document/d/e/2PACX-1vRg7hKBnJ80MhyYrISjxbkf13QVZpInt-D6Fgg32tB_BTJwxDdVVlg3PjHW6Qzv-AlopUPsJDJoajPy/pub)

## Tecnologías

- Backend: Java 21, Spring Boot 3.2.4, Maven 3
- Frontend: Node 18, Next.js 14.2.3 w/React 18
- Base de Datos: MongoDB 7.0

## Cómo correr la aplicación en local

### S3

Para correr un s3 bucket en local, se puede utilizar la imagen de MinIO que se
encuentra en el archivo `compose.yml`:

```bash
docker compose up -d s3
```

Luego, se puede acceder a la consola de MinIO en http://localhost:9001/ con las
credenciales configuradas en el archivo `.env`.

Antes de continuar, debemos crear un bucket público en MinIO llamado con el
mismo nombre que la variable de entorno `S3_BUCKET_NAME` en el archivo `.env`.

### Backend

El backend depende de contar con ambos base de datos y s3 corriendo en local,
por lo que es necesario levantar ambos servicios con:

```sh
docker compose up -d mongo s3
```

Ahora sí, para correr la aplicación en local se debe ejecutar el método main de
la clase `Grupo1Application`. Y con esto ya vamos a tener el backend corriendo
en http://localhost:8080/

Dicho backend se puede probar a través de la colección de Postman que se
encuentra en la carpeta `docs` del proyecto.

### Frontend

El frontend depende del backend, la base de datos y el s3 para correr en local:

```bash
docker compose up -d mongo s3 backend
```

Una vez hecho esto, simplemente ejecutamos el comando:

```bash
npm run dev
```

Y ya vamos a tener la aplicación corriendo en http://localhost:3000/

## Cómo correr la aplicación completa con Docker

El proyecto cuenta con un archivo `compose.yml` que permite correr la
aplicación en varios contenedores de Docker, uno para el backend, otro para el
backend-for-frontend, uno para la base de datos y otro para el s3 bucket.

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

## Integrantes

| Apellido y Nombre | GitHub                                               |
|-------------------|------------------------------------------------------|
| Álvarez, Damián   | [@Damuchi99](https://github.com/Damuchi99)           |
| Galli, Patricio   | [@Patricio-Galli](https://github.com/Patricio-Galli) |
| Lingeri, Martín   | [@MartinLingeri](https://github.com/MartinLingeri)   |
| Pesce, Franco     | [@fpesce27](https://github.com/fpesce27)             |
| Ranieri, Agustín  | [@RaniAgus](https://github.com/RaniAgus)             |
