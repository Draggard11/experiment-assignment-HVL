FROM eclipse-temurin:21-alpine

# switch into the newly created user and directory
WORKDIR /app

# copy the app from the builder image
COPY /build/libs/ExerciseHVL-0.0.1-SNAPSHOT.jar app.jar

# Start the Spring Boot app when the container starts
CMD ["java", "-jar", "app.jar"]