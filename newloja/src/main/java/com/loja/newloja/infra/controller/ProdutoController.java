package com.loja.newloja.infra.controller;

import com.loja.newloja.infra.filter.FindProdutoFilter;
import com.loja.newloja.infra.filter.specification.EventSpecification;
import com.loja.newloja.infra.model.dto.ProdutoRequestDTO;
import com.loja.newloja.infra.model.dto.ProdutoResponseDTO;
import com.loja.newloja.infra.model.entity.produto.ProdutoEntity;
import com.loja.newloja.infra.repository.ProdutoRepository;
import com.loja.newloja.infra.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

	private final ProdutoService produtoService;
	private final ProdutoRepository produtoRepository;

	@PostMapping
	public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody @Valid ProdutoRequestDTO dto) {
		ProdutoResponseDTO produtoCriado = produtoService.criar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
		ProdutoResponseDTO produto = produtoService.buscarPorId(id);
		return ResponseEntity.ok(produto);
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoResponseDTO>> listarTodos(
					@PageableDefault(size = 10, sort = "nome") Pageable pageable
	) {
		Page<ProdutoResponseDTO> produtos = produtoService.listarTodos(pageable);
		return ResponseEntity.ok(produtos);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		produtoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscafilter")
	public ResponseEntity<List<ProdutoEntity>> buscaProduto(FindProdutoFilter filter){
		Specification<ProdutoEntity> spec = EventSpecification.filter(filter);

		List<ProdutoEntity> produto = produtoRepository.findAll(spec);
		return ResponseEntity.ok(produto);
	}


}
