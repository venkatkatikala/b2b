#Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container
COPY target/RequestsAndComplaints-0.0.1-SNAPSHOT.jar /app/Requests.jar

# Expose port 2021
EXPOSE 2021

# Specify the command to run the jar file on port 2021
CMD ["java", "-jar", "Requests.jar", "--server.port=2021"]
