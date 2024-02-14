package com.boaglio.casadocodigo.greendogdelivery.estoque.controller;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.EstoqueRepository;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.ReactiveEstoqueRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EstoqueController {

	private final Logger logger = LoggerFactory.getLogger(EstoqueController.class.getSimpleName());
	@Autowired
	@Qualifier("estoqueRepository")
	EstoqueRepository estoqueRepository;

	@Autowired 
	@Qualifier("reactiveEstoqueRepository")
	ReactiveEstoqueRepository reactiveEstoqueRepository;
	
	@Hidden
	@GetMapping("/lista")
	public List<Estoque> getTodoEstoque() {
		return estoqueRepository.findAll();
	}
	
	@PostMapping("/atualiza")
	public String atualiza(@RequestBody Estoque estoque) {
		logger.info("Recebido via REST: "+estoque);
		estoqueRepository.save(estoque);
		return "Ok";
	}

	@Hidden
	@GetMapping("/")
	@ResponseBody
	public Mono<String> one() {
	    return Mono.just("API ");
	}

	@Hidden
	@GetMapping("/ultimos")
	@ResponseBody
	public Flux<Estoque> ultimos() {
	    return reactiveEstoqueRepository.findTop10ByOrderByIdDesc();
	}

	@Hidden
	@GetMapping(value = "/lista-stream-com-pausa",produces ="text/event-stream")
	public Flux<Estoque> getListaEstoqueStreamComPausa(){
	    return reactiveEstoqueRepository.findTop10ByOrderByIdDesc().delayElements(Duration.ofMillis(300));
	}

	@Hidden
	@GetMapping(path = "/lista-stream", produces = "application/stream+json")
	public Flux<Estoque> getListaEstoqueStream() {
	    return reactiveEstoqueRepository.findAll();
	}

	@Hidden
	@PostMapping("/atualiza-reativo")
	public Mono<String> atualizaReativo(@RequestBody Estoque estoque) {
		logger.info("Recebido via REST: "+estoque);
		estoqueRepository.save(estoque);
		return Mono.just("Ok");
	}
	
}
