package com.vinsguru.booking.service.impl;

import com.vinsguru.booking.service.BookingService;
import com.vinsguru.common.domain.TripStatus;
import com.vinsguru.common.dto.DriverDto;
import com.vinsguru.common.dto.TripRequestDto;
import com.vinsguru.common.dto.TripResponseDto;
import com.vinsguru.common.util.JsonUtil;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class BookingServiceImpl implements BookingService {

    @Value("trip-request-event")
    private String inputTopic;

    @Value("trip-response-event")
    private String outputTopic;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs(KafkaProperties kafkaProperties) {
        Map<String, Object> config = new HashMap<>();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaProperties.getClientId());
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return new KafkaStreamsConfiguration(config);
    }

    @Bean
    public KStream<String, String> kStream(StreamsBuilder kStreamBuilder) {
        KStream<String, String> stream = kStreamBuilder.stream(inputTopic);
        stream.mapValues(tripRequest -> {
                    TripRequestDto requestDto = JsonUtil.stringToObject(tripRequest, TripRequestDto.class);
                    TripResponseDto responseDto = new TripResponseDto(requestDto);
                    responseDto.setDriverDto(new DriverDto("D12", "uber-driver-12"));
                    responseDto.setTripStatus(TripStatus.ACCEPTED);
                    responseDto.setPrice(12.45);
                    return JsonUtil.objectToString(responseDto);
                })
                .to(outputTopic);
        return stream;
    }

    @Override
    public void updateTrip(String tripResponse) {

    }

}
