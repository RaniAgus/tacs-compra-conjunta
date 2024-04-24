FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /build

COPY mvnw pom.xml ./

COPY .mvn/ .mvn

RUN chmod +x mvnw; \
    ./mvnw -B dependency:go-offline

COPY src ./src

RUN ./mvnw package

FROM eclipse-temurin:21-jre-alpine

ARG UID=1001

ARG GID=1001

RUN addgroup -g "$GID" appuser; \
    adduser -u "$UID" -G appuser -D appuser

USER appuser

WORKDIR /home/appuser

COPY --from=builder /build/target/*.jar ./application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]
