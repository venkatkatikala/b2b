# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container
COPY target/AdminModule-EKA-0.0.1-SNAPSHOT.jar /app/Admin.jar

# Expose port 2022
EXPOSE 2022

# Specify the command to run the jar file on port 2022
CMD ["java", "-jar", "Admin.jar", "--server.port=2022"]
