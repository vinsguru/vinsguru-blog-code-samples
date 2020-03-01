package com.vinsguru.kafkastream.processor;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamProcessor {

    @Value("${kafka.topic.input}")
    private String inputTopic;

    @Autowired
    private OddNumberProcessor oddNumberProcessor;

    @Autowired
    private EvenNumberProcessor evenNumberProcessor;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs(KafkaProperties kafkaProperties) {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaProperties.getClientId());
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
        return new KafkaStreamsConfiguration(config);
    }

    @Bean
    public KStream<String, Long> kStream(StreamsBuilder kStreamBuilder) {
        KStream<String, Long> stream = kStreamBuilder.stream(inputTopic);

        Predicate<String, Long> is10 = (k, v) -> v == 10;
        Predicate<String, Long> isDivisibleBy5 = (k, v) -> (v % 5) == 0;
        Predicate<String, Long> isEven = (k, v) -> v % 2 != 0;
        KStream<String, Long>[] branches = stream.branch(is10, isDivisibleBy5, isEven);

        branches[0].mapValues(v -> v + 20).to("double-output");
        branches[1].mapValues(v -> v + 10_000).to("double-output");
        branches[2].mapValues(v -> v * (-1)).to("double-output");

        Arrays.stream(branches)
                .forEach(b -> b.to("double-output"));

      //  stream.;
        return stream;
    }

}