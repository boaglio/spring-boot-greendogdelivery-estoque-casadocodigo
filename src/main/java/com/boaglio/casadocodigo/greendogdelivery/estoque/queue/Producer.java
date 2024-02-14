package com.boaglio.casadocodigo.greendogdelivery.estoque.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.estoque.config.RabbitmqConfig;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.LogFila;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.LogFilaRepository;

@Component
public class Producer {

	private final Logger logger = LoggerFactory.getLogger(Producer.class.getSimpleName());

	@Autowired
	private LogFilaRepository logFilaRepository;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(Estoque message) throws Exception {

		logger.info("Enviando mensagem...");
	
		logFilaRepository.save(new LogFila("Enviando",message.toString()));
		
		rabbitTemplate.convertAndSend(RabbitmqConfig.QUEUE_NAME, message);
		
	}

}