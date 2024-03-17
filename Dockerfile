# Stage 1: Build the application
# Base image
FROM openjdk:21

# Set working directory
WORKDIR /app

# Copy the packaged JAR file into the container
COPY build/libs/LearnEd-0.0.1-SNAPSHOT.jar /app/LearnEd-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "LearnEd-0.0.1-SNAPSHOT.jar"]

# Stage 2: Run the application
#FROM openjdk:21
#WORKDIR /app
#COPY --from=build /app/build/libs/LearnEd-0.0.1-SNAPSHOT.jar ./demo-aws.jar
#EXPOSE 8080
#CMD ["java", "-jar", "demo-aws.jar"]