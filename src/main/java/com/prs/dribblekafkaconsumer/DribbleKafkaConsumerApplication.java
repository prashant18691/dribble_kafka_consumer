package com.prs.dribblekafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;


@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class DribbleKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DribbleKafkaConsumerApplication.class, args);
	}
}
