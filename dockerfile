# Use an official OpenJDK 21 image as the base
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the application JAR file into the container
# Replace 'your-app.jar' with the actual name of your JAR file
COPY target/grocerystore-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the application's port (adjust as needed, e.g., 8080 for Spring Boot)
EXPOSE 8080

# Set the default command to run the application
CMD ["java", "-jar", "/app/app.jar"]
