package com.boaglio.casadocodigo.greendogdelivery.estoque.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.boaglio.casadocodigo.greendogdelivery.estoque.queue.Consumer;

@EnableRabbit
@Configuration
public class RabbitmqConfig {

 	private static final String RECEIVE_MESSAGE = "receiveMessage";

	public static final String EXCHANGE_NAME = "springboot.boaglio.exchange";

	public static final String QUEUE_NAME = "springboot.boaglio.queue";

	public static final String ROUTING_KEY = "springboot.boaglio.#";

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,MessageListenerAdapter listenerAdapter) {
		var container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Consumer consumer) {
		return new MessageListenerAdapter(consumer,RECEIVE_MESSAGE);
	}

}

