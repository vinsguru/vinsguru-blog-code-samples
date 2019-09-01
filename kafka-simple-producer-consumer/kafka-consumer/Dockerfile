# Use JRE8 slim
FROM openjdk:8u191-jre-alpine3.8

# Add the app jar
ADD target/*.jar consumer.jar

ENTRYPOINT java -jar consumer.jar \
                    --spring.kafka.bootstrap-servers=$BROKER \
                    --message.processing.time=$MESSAGE_PROCESSING_TIME