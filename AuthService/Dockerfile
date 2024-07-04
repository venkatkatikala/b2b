#Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container
COPY target/AuthService-0.0.1-SNAPSHOT.jar /app/auth.jar

# Expose port 2055
EXPOSE 2055

# Specify the command to run the jar file on port 2055
CMD ["java", "-jar", "auth.jar", "--server.port=2055"]
