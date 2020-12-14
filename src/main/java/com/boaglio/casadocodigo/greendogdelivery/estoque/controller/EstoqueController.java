package com.boaglio.casadocodigo.greendogdelivery.estoque.controller;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.EstoqueRepository;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.ReactiveEstoqueRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class EstoqueController {

	@Autowired
	@Qualifier("estoqueRepository")
	EstoqueRepository estoqueRepository;

	@Autowired 
	@Qualifier("reactiveEstoqueRepository")
	ReactiveEstoqueRepository reactiveEstoqueRepository;
	
	@GetMapping("/lista")
	public List<Estoque> getTodoEstoque() {
		return estoqueRepository.findAll();
	}
	
	@PostMapping("/atualiza")
	public String atualiza(@RequestBody Estoque estoque) {
		System.out.println("Recebido via REST: "+estoque);
		estoqueRepository.save(estoque);
		return "Ok";
	}
	
	@GetMapping("/")
	@ResponseBody
	public Mono<String> one() {
	    return Mono.just("API ");
	}
	
	@GetMapping("/ultimos")
	@ResponseBody
	public Flux<Estoque> ultimos() {
	    return reactiveEstoqueRepository.findTop10ByOrderByIdDesc();
	}
	
	@GetMapping(value = "/lista-stream-com-pausa",produces ="text/event-stream")
	public Flux<Estoque> getListaEstoqueStreamComPausa(){
	    return reactiveEstoqueRepository.findTop10ByOrderByIdDesc().delayElements(Duration.ofMillis(300));
	}
	
	@GetMapping(path = "/lista-stream", produces = "application/stream+json")
	public Flux<Estoque> getListaEstoqueStream() {
	    return reactiveEstoqueRepository.findAll();
	}
	
	@PostMapping("/atualiza-reativo")
	public Mono<String> atualizaReativo(@RequestBody Estoque estoque) {
		System.out.println("Recebido via REST: "+estoque);
		estoqueRepository.save(estoque);
		return Mono.just("Ok");
	}
	
}
