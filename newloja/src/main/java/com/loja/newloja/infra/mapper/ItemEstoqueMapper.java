package com.loja.newloja.infra.mapper;

import com.loja.newloja.infra.model.dto.ItemEstoqueRequestDTO;
import com.loja.newloja.infra.model.dto.ItemEstoqueResponseDTO;
import com.loja.newloja.infra.model.entity.produto.ItemEstoqueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "string")
public interface ItemEstoqueMapper {

	ItemEstoqueResponseDTO toDto(ItemEstoqueEntity itemEstoque);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "produto", ignore = true)
	ItemEstoqueEntity toEntity(ItemEstoqueRequestDTO dto);
}