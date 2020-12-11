package com.boaglio.casadocodigo.greendogdelivery.estoque;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.queue.Producer;

import reactor.core.publisher.Flux;

@SpringBootTest
class EstoqueGreenDogDeliveryApplicationTests {

	@Autowired
	private Producer producer;

	@Test
	void enviaMensagem() throws Exception {

		Estoque estoqueTeste = new Estoque(1l, 10l);

		producer.send(estoqueTeste);

	}

	@Test
	void buscaUltimos() {

		WebClient client = WebClient.create("http://localhost:9000");
		
		Flux<Estoque> employeeFlux = client.get().uri("/api/ultimos").retrieve().bodyToFlux(Estoque.class);
		
		employeeFlux.subscribe(System.out::println);

	}

}
