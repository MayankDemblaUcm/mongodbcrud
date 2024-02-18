# Use the official OpenJDK base image with Java 17
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at the specified working directory
COPY target/projectwork-0.0.1.jar /app/app.jar

# Expose the port that your Spring Boot application uses (default is 8080)
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
