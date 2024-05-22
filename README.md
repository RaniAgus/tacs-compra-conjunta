# TP TACS 2024-C1 - Compra Conjunta (Grupo 1)

El objetivo del TP es desarrollar una aplicación que sirva para realizar una
compra de un producto entre varias personas.
[Ver enunciado](https://docs.google.com/document/d/e/2PACX-1vRg7hKBnJ80MhyYrISjxbkf13QVZpInt-D6Fgg32tB_BTJwxDdVVlg3PjHW6Qzv-AlopUPsJDJoajPy/pub)

## Tecnologías

- Backend: Java 21, Spring Boot 3.2.4, Maven 3
- Frontend: Node 18, Next.js 14.2.3 w/React 18
- Base de Datos: ElasticSearch 8.13.4

## Cómo correr la aplicación en local

### Base de Datos

Para correr la base de datos en local, se puede utilizar el archivo
`elastic.compose.yml` que se encuentra en la raíz del proyecto:

```bash
docker compose -f elastic.compose.yml up -d
```

Y ya vamos a tener la base de datos corriendo en http://localhost:9200/. Podemos
acceder a la misma a través de Kibana en http://localhost:5601/.

### Backend

El backend depende de contar con una base de datos ElasticSearch corriendo en
local, por lo que es necesario correr el comando anterior antes de correr la
aplicación.

Luego, antes de correr la aplicación, es necesario extraer el certificado de
seguridad de la base de datos. Para ello, contamos con un script:

```bash
./scripts/extract-cert.sh
```

Este script extrae el certificado de la base de datos y lo guarda en la carpeta
`certs` del proyecto.

Ahora sí, para correr la aplicación en local se debe ejecutar el método main de
la clase `Grupo1Application`. Y con esto ya vamos a tener el backend corriendo
en http://localhost:8080/

### Frontend

El frontend depende de ambos backend y base de datos corriendo en local. Para
ello, contamos con otro docker compose file con ambos servicios:

```bash
docker compose -f backend.compose.yml up
```

Una vez hecho esto, simplemente ejecutamos el comando:

```bash
npm run dev
```

Y ya vamos a tener la aplicación corriendo en http://localhost:3000/

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
