# Use an official Maven image to build the spring Boot app
FROM maven:3.9.6-eclipse-temurin-17 AS build

#Set the working directory
WORKDIR /app

#Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the applciation
COPY src ./src
RUN mvn clean package -DskipTests

#Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/ToDoApp-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose port
EXPOSE 8080

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "app/ToDoApp-0.0.1-SNAPSHOT.jar"]