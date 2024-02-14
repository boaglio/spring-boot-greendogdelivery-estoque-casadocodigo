package com.boaglio.casadocodigo.greendogdelivery.estoque.queue;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.LogFila;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.EstoqueRepository;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.LogFilaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Consumer.class.getSimpleName());
	@Autowired
	private LogFilaRepository logFilaRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@RabbitListener(queues = { "springboot.boaglio.queue" })
	public void receiveMessage(@Payload Estoque mensagem) {

		logger.info("Recebido via fila: <" + mensagem + ">");
		
		logFilaRepository.save(new LogFila("Recebendo", mensagem.toString()));

		logger.info("Gravando: <" + mensagem + ">");
		
		estoqueRepository.save(mensagem);

	}
}