package com.parthu.kafka.stream;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.parthu.kafka.producer.WikimediaProducer;

@Service
public class WikimediaStreamConsumer {

	private final WebClient webClient;

	private final WikimediaProducer wikimediaProducer;

	public WikimediaStreamConsumer(WebClient.Builder webClientBuilder, WikimediaProducer wikimediaProducer) {
		this.webClient = webClientBuilder.baseUrl("https://stream.wikimedia.org/v2").build();
		this.wikimediaProducer = wikimediaProducer;

	}

	public void consumeStreamPublish() {
		webClient.get()
		.uri("/stream/recentchange")
		.retrieve()
		.bodyToFlux(String.class)
		.subscribe(wikimediaProducer::sendMessage);
		
	}
}
