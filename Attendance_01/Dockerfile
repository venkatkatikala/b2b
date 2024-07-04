#Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container
COPY target/Attendance_01-0.0.1-SNAPSHOT.jar /app/Attendence.jar

# Expose port 1998
EXPOSE 1998

# Specify the command to run the jar file on port 1998
CMD ["java", "-jar", "Attendence.jar", "--server.port=1998"]
