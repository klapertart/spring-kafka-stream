package klapertart.lab.kafkastream.properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KafkaPropertiesTest {
    @Autowired
    private KafkaProperties kafkaProperties;

    @Test
    @Disabled
    public void checkMapping(){
        Assertions.assertEquals("localhost:9092",kafkaProperties.getBrokersUrl());
        Assertions.assertEquals("onboarding-stream-input-topic",kafkaProperties.getTopicInput());
        Assertions.assertEquals("onboarding-stream-output-topic",kafkaProperties.getTopicOutput());
    }
}
