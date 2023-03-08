package klapertart.lab.kafkastream;

import klapertart.lab.kafkastream.processors.WordCountProcessor;
import klapertart.lab.kafkastream.properties.KafkaProperties;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;

/**
 * @author kurakuraninja
 * @since 06/03/2023
 */

@SpringBootTest
public class TopologyTest {

    @Autowired
    KafkaProperties kafkaProperties;

    @Autowired
    WordCountProcessor wordCountProcessor;

    @Test
    @Disabled
    void givenInputMessages_whenProcessed_thenWordCountIsProduced(){
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        wordCountProcessor.buildPipeline(streamsBuilder);
        Topology topology = streamsBuilder.build();

        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG,"stream-app");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,kafkaProperties.getBrokersUrl());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());


        try(TopologyTestDriver topologyTestDriver = new TopologyTestDriver(topology,config)) {

            TestInputTopic<String, String> inputTopic = topologyTestDriver.createInputTopic(
                    kafkaProperties.getTopicInput(), Serdes.String().serializer(), Serdes.String().serializer()
            );

            TestOutputTopic<String, Long> outputTopic = topologyTestDriver.createOutputTopic(
                    kafkaProperties.getTopicOutput(), Serdes.String().deserializer(), Serdes.Long().deserializer()
            );

            inputTopic.pipeInput("key", "martabak");
            inputTopic.pipeInput("key2", "martabak asin martabak asin");

            Assertions.assertThat(outputTopic.readKeyValuesToList()).containsExactly(
                    KeyValue.pair("martabak", 1L),
                    KeyValue.pair("martabak", 2L),
                    KeyValue.pair("asin", 1L),
                    KeyValue.pair("martabak", 3L),
                    KeyValue.pair("asin", 2L)
            );

        }
    }
}
