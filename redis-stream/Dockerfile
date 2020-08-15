# Use JRE11 slim
FROM openjdk:11.0-jre-slim

# Add the app jar
ADD target/*.jar redis-stream.jar

ENTRYPOINT java -jar redis-stream.jar