package com.boaglio.casadocodigo.greendogdelivery.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.LogFila;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.LogFilaRepository;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/api")
public class LogController {

	@Autowired
	LogFilaRepository logRepository;

	@GetMapping("/logs")
	public List<LogFila> getLogs() {
		return logRepository.findAll();
	}
	
}
