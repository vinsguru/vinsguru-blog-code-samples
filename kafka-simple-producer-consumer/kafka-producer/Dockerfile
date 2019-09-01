# Use JRE8 slim
FROM openjdk:8u191-jre-alpine3.8

# Add the app jar
ADD target/*.jar producer.jar

ENTRYPOINT java -jar producer.jar \
                    --spring.kafka.bootstrap-servers=$BROKER