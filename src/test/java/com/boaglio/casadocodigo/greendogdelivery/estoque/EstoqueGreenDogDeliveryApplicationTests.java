package com.boaglio.casadocodigo.greendogdelivery.estoque;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.queue.Producer;

@SpringBootTest
class EstoqueGreenDogDeliveryApplicationTests {

	@Autowired
	private Producer producer;	

	@Test
	void enviaMensagem() throws Exception {
		
		Estoque estoqueTeste = new Estoque(1l,10l);
		
		producer.send(estoqueTeste);
		
	}

}
