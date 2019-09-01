# Use JRE8 slim
FROM openjdk:8u191-jre-alpine3.8

# Add the app jar
ADD target/*.jar app.jar

ENTRYPOINT sleep ${START_DELAY:-0} && java -jar app.jar