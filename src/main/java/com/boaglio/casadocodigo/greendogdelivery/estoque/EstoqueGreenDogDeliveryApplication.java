package com.boaglio.casadocodigo.greendogdelivery.estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.boaglio") 
@EnableReactiveMongoRepositories("com.boaglio") 
@ComponentScan(basePackages = "com.boaglio")
public class EstoqueGreenDogDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstoqueGreenDogDeliveryApplication.class, args);
	}

}
