#Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container
COPY target/AnnouncementsModule-0.0.1-SNAPSHOT.jar /app/Announcement.jar

# Expose port 2020
EXPOSE 2020

# Specify the command to run the jar file on port 2020
CMD ["java", "-jar", "Announcement.jar", "--server.port=2020"]
