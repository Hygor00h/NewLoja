package com.loja.newloja.infra.service;

import com.loja.newloja.infra.model.dto.ProdutoRequestDTO;
import com.loja.newloja.infra.model.dto.ProdutoResponseDTO;
import com.loja.newloja.infra.model.entity.produto.CategoriaEntity;
import com.loja.newloja.infra.model.entity.produto.ProdutoEntity;
import com.loja.newloja.infra.exceptions.ResourceNotFoundException;
import com.loja.newloja.infra.mapper.ProdutoMapper;
import com.loja.newloja.infra.repository.CategoriaRepository;
import com.loja.newloja.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final CategoriaRepository categoriaRepository;
	private final ProdutoMapper produtoMapper;

	@Transactional
	public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
		CategoriaEntity categoria = categoriaRepository.findById(dto.categoriaId())
						.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com o ID: " + dto.categoriaId()));

		ProdutoEntity produto = produtoMapper.toEntity(dto);
		produto.setCategoria(categoria);

		ProdutoEntity produtoSalvo = produtoRepository.save(produto);
		return produtoMapper.toDto(produtoSalvo);
	}

	@Transactional(readOnly = true)
	public ProdutoResponseDTO buscarPorId(Long id) {
		ProdutoEntity produto = produtoRepository.findByIdWithCategoriaAndEstoque(id)
						.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o ID: " + id));

		return produtoMapper.toDto(produto);
	}

	@Transactional(readOnly = true)
	public Page<ProdutoResponseDTO> listarTodos(Pageable pageable) {
		return produtoRepository.findAll(pageable)
						.map(produtoMapper::toDto);
	}

	@Transactional
	public void deletar(Long id) {
		if (!produtoRepository.existsById(id)) {
			throw new ResourceNotFoundException("Produto não encontrado com o ID: " + id);
		}
		produtoRepository.deleteById(id);
	}
}
