package klapertart.lab.kafkastream.processors;

import klapertart.lab.kafkastream.properties.KafkaProperties;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Stream;

@Component
public class WordCountProcessor {

    @Autowired
    private KafkaProperties kafkaProperties;

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    public void buildPipeline(StreamsBuilder streamsBuilder){

        KStream<String, String> messageStream = streamsBuilder.stream(kafkaProperties.getTopicInput(), Consumed.with(STRING_SERDE,STRING_SERDE));

        KTable<String, Long> wordCounts = messageStream
                .mapValues(text -> text.toLowerCase())
                .flatMapValues(value -> Arrays.asList(value.split("\\W+")))
                .groupBy((key, value) -> value)
                .count(Materialized.as("counts"));

        // print to console
        wordCounts.toStream().foreach((key,count) -> System.out.println("WORD : " + key + ", COUNT: " + count));

        // send to kafka
        wordCounts.toStream().to(kafkaProperties.getTopicOutput(),Produced.with(Serdes.String(), Serdes.Long()));
    }
}
