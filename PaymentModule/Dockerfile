#Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application's jar file into the container
COPY target/PaymentModule-0.0.1-SNAPSHOT.jar /app/Payment.jar

# Expose port 2025
EXPOSE 2025

# Specify the command to run the jar file on port 2025
CMD ["java", "-jar", "Payment.jar", "--server.port=2025"]
