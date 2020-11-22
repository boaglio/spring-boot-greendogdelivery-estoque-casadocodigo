package com.boaglio.casadocodigo.greendogdelivery.estoque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;

public interface EstoqueRepository extends MongoRepository<Estoque, String> {

}
