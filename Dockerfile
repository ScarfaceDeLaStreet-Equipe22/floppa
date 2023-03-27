FROM maven:3.9-amazoncorretto-11
WORKDIR /app

COPY . .

RUN mvn compile

CMD ["mvn", "exec:java"]
