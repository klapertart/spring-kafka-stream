package klapertart.lab.kafkastream.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author kurakuraninja
 * @since 20/02/23
 */

@Component
@ConfigurationProperties("kafka")
@Getter
@Setter
public class KafkaProperties {
    private String brokersUrl;
    private String topicInput;
    private String topicOutput;
}
