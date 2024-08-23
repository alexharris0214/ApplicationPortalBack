FROM gradle:jdk21-alpine as builder

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/
COPY settings.gradle /app/

COPY src /app/src

RUN ./gradlew build 

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]