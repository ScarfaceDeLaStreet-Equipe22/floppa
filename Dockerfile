# j'essaie de faire un multi-stage config :
#FROM maven:3.9-amazoncorretto-11
#WORKDIR /app-build
#COPY . .
#RUN mvn compile
#CMD ["mvn", "exec:java"]

# ==== MULTI-STAGE CONFIG ====
# Building the jar file
FROM maven:3.9-amazoncorretto-11 as builder
WORKDIR /app-build
COPY . .
RUN mvn package
RUN cp target/jersey-api-*-with-dependencies.jar app.jar

# Running the app from the jar file
FROM amazoncorretto:11
WORKDIR /app
COPY --from=builder /app-build/app.jar .

EXPOSE 8080


CMD ["java", "-jar", "app.jar"]

#docker build . -t floppa
#docker run --rm -it -p 18080:8080 floppa
#ps j'ai changer l'adresse de base du main pour que ca marche avec docker