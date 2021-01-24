package com.boaglio.casadocodigo.greendogdelivery.estoque;

import java.time.Duration;

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

		System.out.println("------ Lista Ultimos ------");
		
		WebClient client = WebClient.create("http://localhost:9000");
		
		Flux<Estoque> ultimosFlux = client.get().uri("/api/ultimos").retrieve().bodyToFlux(Estoque.class);
		
		ultimosFlux.subscribe(System.out::println);

	}

	@Test
	void buscaListaEstoqueStream() {
		
		System.out.println("------ Lista Estoque com Stream ------");

		WebClient client = WebClient.create("http://localhost:9000");
		
		Flux<Estoque> ultimosFlux = client.get().uri("/api/lista-stream").retrieve().bodyToFlux(Estoque.class);
		
		ultimosFlux.subscribe(System.out::println);
		
		// teste com:  curl -v http://localhost:9000/api/lista-stream

	}
	
	@Test
	void buscaListaEstoqueStreamComPausa() {

		System.out.println("------ Lista Estoque com Stream Pausado ------");
        
		WebClient client = WebClient.create("http://localhost:9000");
		
		Flux<Estoque> ultimosFlux = client.get().uri("/api/lista-stream-com-pausa").retrieve().bodyToFlux(Estoque.class).timeout(Duration.ofMinutes(5));
		
		ultimosFlux.subscribe(System.out::println);

		// teste com:  curl -v http://localhost:9000/api/lista-stream-com-pausa

	}
	
}
