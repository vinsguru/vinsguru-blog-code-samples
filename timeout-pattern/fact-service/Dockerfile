FROM openjdk:8-jre-slim

ADD target/fact-service-*.jar fact-service.jar

EXPOSE 8080

ENTRYPOINT java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} fact-service.jar