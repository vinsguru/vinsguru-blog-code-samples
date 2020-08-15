FROM openjdk:8-jre-slim

ADD target/compute-service-*.jar compute-service.jar

EXPOSE 8080

ENTRYPOINT java -jar -Dspring.profiles.active=${ACTIVE_PROFILE} compute-service.jar