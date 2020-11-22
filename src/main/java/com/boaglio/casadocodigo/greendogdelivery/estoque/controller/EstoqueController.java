package com.boaglio.casadocodigo.greendogdelivery.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boaglio.casadocodigo.greendogdelivery.estoque.domain.Estoque;
import com.boaglio.casadocodigo.greendogdelivery.estoque.repository.EstoqueRepository;

@RestController
@RequestMapping("/api")
public class EstoqueController {

	@Autowired
	EstoqueRepository estoqueRepository;

	@GetMapping("/lista")
	public List<Estoque> getTodoEstoque() {
		return estoqueRepository.findAll();
	}
	
	@GetMapping("/atualiza")
	public String createForm(@ModelAttribute Estoque estoque) {
		estoqueRepository.save(estoque);
		return "Ok";
	}
}
