package com.boaglio.casadocodigo.greendogdelivery.estoque.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.LogFila;

public interface LogFilaRepository extends MongoRepository<LogFila, String> {

}
