package com.boaglio.casadocodigo.greendogdelivery.estoque;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.queue.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@SpringBootTest
class EstoqueGreenDogDeliveryApplicationTests {

	@Autowired
	private Producer producer;

	@Test
	void enviaMensagem() throws Exception {

		var estoqueTeste = new Estoque(1L, 10L);

		producer.send(estoqueTeste);

	}

	@Test
	void buscaUltimos() {

		System.out.println("------ Lista Ultimos ------");
		
		var client = WebClient.create("http://localhost:9000");
		
		var ultimosFlux = client.get().uri("/api/ultimos").retrieve().bodyToFlux(Estoque.class);
		
		ultimosFlux.subscribe(System.out::println);

	}

	@Test
	void buscaListaEstoqueStream() {
		
		System.out.println("------ Lista Estoque com Stream ------");

		var client = WebClient.create("http://localhost:9000");
		
		var ultimosFlux = client.get().uri("/api/lista-stream").retrieve().bodyToFlux(Estoque.class);
		
		ultimosFlux.subscribe(System.out::println);
		
		// teste com:  curl -v http://localhost:9000/api/lista-stream

	}
	
	@Test
	void buscaListaEstoqueStreamComPausa() {

		System.out.println("------ Lista Estoque com Stream Pausado ------");
        
		var client = WebClient.create("http://localhost:9000");
		
		var ultimosFlux = client.get().uri("/api/lista-stream-com-pausa").retrieve().bodyToFlux(Estoque.class).timeout(Duration.ofMinutes(5));
		
		ultimosFlux.subscribe(System.out::println);

		// teste com:  curl -v http://localhost:9000/api/lista-stream-com-pausa

	}
	
}
