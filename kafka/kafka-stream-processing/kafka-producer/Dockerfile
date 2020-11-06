FROM openjdk:11.0.7-jre-slim

WORKDIR /usr/app

ADD target/grpc*.jar app.jar

ENTRYPOINT java -jar app.jar