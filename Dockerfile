# Do compilation inside a (throw-away) builder container
FROM gradle as builder

# copy only necessary source code files
COPY settings.gradle.kts build.gradle.kts

# Copying a folder recursively need special attention
COPY src src
COPY gradle gradle

# compile with gradle
RUN gradle clean
RUN gradle bootJar

# rename and move the resulting JAR file
RUN mv build/libs/ExerciseHVL-0.0.1-SNAPSHOT.jar app.jar


# This will be the base image for the running application
FROM eclipse-temurin:21-alpine

# switch into the newly created user and directory
USER app
WORKDIR /app

# copy the app from the builder image
COPY --from=builder /home/gradle/app.jar .

# Start the Spring Boot app when the container starts
CMD ["java", "-jar", "app.jar"]