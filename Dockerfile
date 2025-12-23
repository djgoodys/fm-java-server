# Use an official OpenJDK runtime as a parent image
#FROM openjdk:17-jdk-slim
FROM eclipse-temurin:17-jdk-slim

# Set the working directory inside the container
WORKDIR /fmjavaserver

# Copy the JAR file into the container
COPY target/fmjavaserver-0.0.1-SNAPSHOT.jar /fmjavaserver/fmjavaserver.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/fmjavaserver/fmjavaserver.jar"]
