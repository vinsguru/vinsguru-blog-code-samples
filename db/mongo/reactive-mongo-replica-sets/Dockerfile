FROM openjdk:11.0.6-jre-slim

WORKDIR /usr/app

ADD target/*jar app.jar

CMD sleep 10 && java -jar app.jar