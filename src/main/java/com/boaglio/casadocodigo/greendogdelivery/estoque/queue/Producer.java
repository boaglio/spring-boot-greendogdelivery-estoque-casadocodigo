package com.boaglio.casadocodigo.greendogdelivery.estoque.queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.estoque.config.RabbitmqConfig;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.LogFila;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.LogFilaRepository;

@Component
public class Producer {
	
	@Autowired
	private LogFilaRepository logFilaRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(Estoque message) throws Exception { 
		
		System.out.println("Enviando mensagem...");
	
		logFilaRepository.save(new LogFila("Enviando",message.toString()));
		
		rabbitTemplate.convertAndSend(RabbitmqConfig.QUEUE_NAME, message);
		
	}

}