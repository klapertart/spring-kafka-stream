package klapertart.lab.kafkastream;

import klapertart.lab.kafkastream.properties.KafkaProperties;
import klapertart.lab.kafkastream.properties.SslProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class BelajarKafkaStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarKafkaStreamApplication.class, args);
	}

}
