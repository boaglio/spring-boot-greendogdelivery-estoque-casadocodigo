package com.boaglio.casadocodigo.greendogdelivery.estoque.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.LogFila;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.EstoqueRepository;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.LogFilaRepository;

@Component
public class Consumer {

	@Autowired
	private LogFilaRepository logFilaRepository;

	@Autowired
	private EstoqueRepository estoqueRepository;

	@RabbitListener(queues = { "springboot.boaglio.queue" })
	public void receiveMessage(@Payload Estoque mensagem) {

		System.out.println("Recebido via fila: <" + mensagem + ">");
		
		logFilaRepository.save(new LogFila("Recebendo", mensagem.toString()));

		System.out.println("Gravando: <" + mensagem + ">");
		
		estoqueRepository.save(mensagem);

	}
}