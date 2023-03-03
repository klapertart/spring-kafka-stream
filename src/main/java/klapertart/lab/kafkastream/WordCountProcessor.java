package klapertart.lab.kafkastream;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class WordCountProcessor {
    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder){
        KStream<String, String> messageStream = streamsBuilder.stream("input-topic", Consumed.with(STRING_SERDE,STRING_SERDE));

        KTable<String, Long> wordCounts = messageStream
                .mapValues(text -> text.toLowerCase())
                .flatMapValues(value -> Arrays.asList(value.split("\\W+")))
                .groupBy((key, value) -> value)
                .count();

        // print to console
        wordCounts.toStream().foreach((key,count) -> System.out.println("WORD : " + key + ", COUNT: " + count));

        // send to kafka
        wordCounts.toStream().to("output-topic",Produced.with(Serdes.String(), Serdes.Long()));

    }
}
