package com.boaglio.casadocodigo.greendogdelivery.estoque.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;

import reactor.core.publisher.Flux;

public interface ReactiveEstoqueRepository extends ReactiveCrudRepository<Estoque, String> {

	Flux<Estoque> findAll();
	
	Flux<Estoque> findTop10ByOrderByIdDesc();

}
