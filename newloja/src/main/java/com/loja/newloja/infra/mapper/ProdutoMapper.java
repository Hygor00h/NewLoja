package com.loja.newloja.infra.mapper;

import com.loja.newloja.infra.model.dto.ProdutoRequestDTO;
import com.loja.newloja.infra.model.dto.ProdutoResponseDTO;
import com.loja.newloja.infra.model.entity.produto.ProdutoEntity;
import org.mapstruct.*;

@Mapper
public interface ProdutoMapper {

	@Mapping(target = "nomeCategoria", source = "categoria.nome")
	ProdutoResponseDTO toDto(ProdutoEntity produto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dataCriacao", ignore = true)
	@Mapping(target = "categoria", ignore = true)
	ProdutoEntity toEntity(ProdutoRequestDTO dto);

	@AfterMapping
	default void vincularEstoqueAoProduto(@MappingTarget ProdutoEntity produto) {
		if (produto.getEstoque() != null) {
			produto.getEstoque().forEach(item -> item.setProduto(produto));
		}
	}
}