package com.parthu.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WikimediaProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg) {
		// log.info(format("sending message to john Topic",msg));
		kafkaTemplate.send("wikimedia-stream", msg);
	}
}
