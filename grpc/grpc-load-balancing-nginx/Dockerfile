FROM openjdk:11.0.7-jre-slim

WORKDIR /usr/app

ADD target/grpc*.jar app.jar

EXPOSE 6565

ENTRYPOINT java -jar app.jar