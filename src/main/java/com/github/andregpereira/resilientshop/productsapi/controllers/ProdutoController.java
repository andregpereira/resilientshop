package com.github.andregpereira.resilientshop.productsapi.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.andregpereira.resilientshop.productsapi.dtos.produto.ProdutoDetalhesDto;
import com.github.andregpereira.resilientshop.productsapi.dtos.produto.ProdutoDto;
import com.github.andregpereira.resilientshop.productsapi.dtos.produto.ProdutoRegistroDto;
import com.github.andregpereira.resilientshop.productsapi.services.produto.ProdutoConsultaService;
import com.github.andregpereira.resilientshop.productsapi.services.produto.ProdutoManutencaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoManutencaoService manutencaoService;

	@Autowired
	private ProdutoConsultaService consultaService;

	// Registrar produto
	@PostMapping
	public ResponseEntity<ProdutoDetalhesDto> registrar(@RequestBody @Valid ProdutoRegistroDto dto,
			UriComponentsBuilder uriBuilder) {
		ProdutoDetalhesDto produto = manutencaoService.registrar(dto);
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.id()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	// Atualizar produto por id
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDetalhesDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid ProdutoRegistroDto dto) {
		return ResponseEntity.ok(manutencaoService.atualizar(id, dto));
	}

	// Remover por id
	@DeleteMapping("/{id}")
	private ResponseEntity<String> remover(@PathVariable Long id) {
		return ResponseEntity.ok(manutencaoService.remover(id));
	}

	// Pesquisar por id
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDetalhesDto> consultarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(consultaService.consultarPorId(id));
	}

	// Pesquisar por nome
	@GetMapping("/nome")
	public ResponseEntity<Page<ProdutoDto>> consultarPorNome(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "nome", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
		return ResponseEntity.ok(consultaService.consultarPorNome(nome, pageable));
	}

}
