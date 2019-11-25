# Use JRE8 slim
FROM openjdk:8u191-jre-alpine3.8

# Add the app jar
ADD target/*.jar app.jar

ENTRYPOINT java -jar app.jar \
    --app.write.enabled=${WRITE_NODE:-false}