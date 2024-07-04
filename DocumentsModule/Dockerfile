#Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container
COPY target/DocumentsModule-0.0.1-SNAPSHOT.jar /app/Document.jar

# Expose port 9999
EXPOSE 9999

# Specify the command to run the jar file on port 9999
CMD ["java", "-jar", "Document.jar", "--server.port=9999"]
