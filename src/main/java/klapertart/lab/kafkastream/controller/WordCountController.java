package klapertart.lab.kafkastream.controller;

import klapertart.lab.kafkastream.messages.ResponseData;
import klapertart.lab.kafkastream.messages.ResponseGeneric;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api")
public class WordCountController {

    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

    @GetMapping("/count/{word}")
    public ResponseEntity<ResponseGeneric> getWordCount(@PathVariable String word) {
        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, Long> counts = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType("counts", QueryableStoreTypes.keyValueStore())
        );

        Long count = counts.get(word);
        Optional<Long> countWord = Optional.ofNullable(count);
        if (!countWord.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseGeneric(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name()));
        }

        List<Long> words = new ArrayList<>();
        words.add(count);
        return  ResponseEntity.ok(new ResponseData<Long>(HttpStatus.OK.value(), HttpStatus.OK.name(), words));

    }
}
