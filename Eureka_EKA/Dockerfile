#Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container
COPY target/Eureka_EKA-0.0.1-SNAPSHOT.jar /app/EUREKA.jar

# Expose port 8761
EXPOSE 8761

# Specify the command to run the jar file on port 8761
CMD ["java", "-jar", "EUREKA.jar", "--server.port=8761"]
