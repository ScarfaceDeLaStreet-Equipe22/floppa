# Building the jar file
FROM maven:3.9-amazoncorretto-11
WORKDIR /app-build
COPY . .
RUN mvn package
RUN cp target/jersey-api-*-with-dependencies.jar app.jar

# Running the app from the jar file
FROM amazoncorretto:11
WORKDIR /app
COPY --from=0 /app-build/app.jar .

CMD ["java", "-jar", "app.jar"]