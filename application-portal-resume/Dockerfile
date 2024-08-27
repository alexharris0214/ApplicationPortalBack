# Use a base image with JDK 21
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file to the container
COPY build/libs/application-portal-resume-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the service will run on (replace with the correct port for each service)
EXPOSE 8083

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]